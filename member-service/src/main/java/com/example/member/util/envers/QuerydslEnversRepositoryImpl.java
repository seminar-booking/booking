package com.example.member.util.envers;

import org.hibernate.envers.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.envers.repository.support.DefaultRevisionMetadata;
import org.springframework.data.history.*;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.history.support.RevisionEntityInformation;
import org.springframework.data.util.StreamUtils;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.*;

public class QuerydslEnversRepositoryImpl<T, ID extends Serializable, N extends Number & Comparable<N>> extends QuerydslJpaRepository<T, ID>
        implements RevisionRepository<T, ID, N> {

    private final EntityInformation<T, ?> entityInformation;
    private final RevisionEntityInformation revisionEntityInformation;
    private final EntityManager entityManager;

    public QuerydslEnversRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, RevisionEntityInformation revisionEntityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        Assert.notNull(revisionEntityInformation, "RevisionEntityInformation must not be null!");
        this.entityInformation = entityInformation;
        this.revisionEntityInformation = revisionEntityInformation;
        this.entityManager = entityManager;
    }

    public QuerydslEnversRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager, RevisionEntityInformation revisionEntityInformation, EntityPathResolver resolver) {
        super(entityInformation, entityManager, resolver);
        Assert.notNull(revisionEntityInformation, "RevisionEntityInformation cannot be null");
        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
        this.revisionEntityInformation = revisionEntityInformation;
    }

    /*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.history.RevisionRepository#findLastChangeRevision(java.io.Serializable)
	 */
    @SuppressWarnings("unchecked")
    public Optional<Revision<N, T>> findLastChangeRevision(ID id) {

        Class<T> type = entityInformation.getJavaType();
        AuditReader reader = AuditReaderFactory.get(entityManager);

        List<Number> revisions = reader.getRevisions(type, id);

        if (revisions.isEmpty()) {
            return Optional.empty();
        }

        N latestRevision = (N) revisions.get(revisions.size() - 1);

        Class<?> revisionEntityClass = revisionEntityInformation.getRevisionEntityClass();

        Object revisionEntity = reader.findRevision(revisionEntityClass, latestRevision);
        RevisionMetadata<N> metadata = (RevisionMetadata<N>) getRevisionMetadata(revisionEntity);

        return Optional.of(Revision.of(metadata, reader.find(type, id, latestRevision)));
    }

    /*
	 * (non-Javadoc)
	 * @see org.springframework.data.envers.repository.support.EnversRevisionRepository#findRevision(java.io.Serializable, java.lang.Number)
	 */
    @Override
    public Optional<Revision<N, T>> findRevision(ID id, N revisionNumber) {

        Assert.notNull(id, "Identifier must not be null!");
        Assert.notNull(revisionNumber, "Revision number must not be null!");

        return getEntityForRevision(revisionNumber, id, AuditReaderFactory.get(entityManager));

    }

    /*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.history.RevisionRepository#findRevisions(java.io.Serializable)
	 */
    @SuppressWarnings("unchecked")
    public Revisions<N, T> findRevisions(ID id) {

        Class<T> type = entityInformation.getJavaType();
        AuditReader reader = AuditReaderFactory.get(entityManager);
        List<? extends Number> revisionNumbers = reader.getRevisions(type, id);

        return revisionNumbers.isEmpty() ? Revisions.none()
                : getEntitiesForRevisions((List<N>) revisionNumbers, id, reader);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.history.RevisionRepository#findRevisions(java.io.Serializable, org.springframework.data.domain.Pageable)
     */
    @SuppressWarnings("unchecked")
    public Page<Revision<N, T>> findRevisions(ID id, Pageable pageable) {

        Class<T> type = entityInformation.getJavaType();
        AuditReader reader = AuditReaderFactory.get(entityManager);
        List<Number> revisionNumbers = reader.getRevisions(type, id);
        boolean isDescending = RevisionSort.getRevisionDirection(pageable.getSort()).isDescending();

        if (isDescending) {
            Collections.reverse(revisionNumbers);
        }

        if (pageable.getOffset() > revisionNumbers.size()) {
            return new PageImpl<>(Collections.emptyList(), pageable, 0);
        }

        long upperBound = pageable.getOffset() + pageable.getPageSize();
        upperBound = upperBound > revisionNumbers.size() ? revisionNumbers.size() : upperBound;

        List<? extends Number> subList = revisionNumbers.subList(toInt(pageable.getOffset()), toInt(upperBound));
        Revisions<N, T> revisions = getEntitiesForRevisions((List<N>) subList, id, reader);

        revisions = isDescending ? revisions.reverse() : revisions;

        return new PageImpl<>(revisions.getContent(), pageable, revisionNumbers.size());
    }

    /**
     * Returns the entities in the given revisions for the entitiy with the given id.
     *
     * @param revisionNumbers
     * @param id
     * @param reader
     * @return
     */
    @SuppressWarnings("unchecked")
    private Revisions<N, T> getEntitiesForRevisions(List<N> revisionNumbers, ID id, AuditReader reader) {

        Class<T> type = entityInformation.getJavaType();
        Map<N, T> revisions = new HashMap<>(revisionNumbers.size());

        Class<?> revisionEntityClass = revisionEntityInformation.getRevisionEntityClass();
        Map<Number, Object> revisionEntities = (Map<Number, Object>) reader.findRevisions(revisionEntityClass,
                new HashSet<>(revisionNumbers));

        for (Number number : revisionNumbers) {
            revisions.put((N) number, reader.find(type, type.getName(), id, number, true));
        }

        return Revisions.of(toRevisions(revisions, revisionEntities));
    }

    /**
     * Returns an entity in the given revision for the given entity-id.
     *
     * @param revisionNumber
     * @param id
     * @param reader
     * @return
     */
    @SuppressWarnings("unchecked")
    private Optional<Revision<N, T>> getEntityForRevision(N revisionNumber, ID id, AuditReader reader) {

        Class<?> type = revisionEntityInformation.getRevisionEntityClass();

        T revision = (T) reader.findRevision(type, revisionNumber);
        Optional<Object> entity = Optional.ofNullable(reader.find(entityInformation.getJavaType(), id, revisionNumber));

        return entity.map(it -> Revision.of((RevisionMetadata<N>) getRevisionMetadata(revision), (T) it));
    }

    @SuppressWarnings("unchecked")
    private List<Revision<N, T>> toRevisions(Map<N, T> source, Map<Number, Object> revisionEntities) {

        return source.entrySet().stream() //
                .map(entry -> Revision.of( //
                        (RevisionMetadata<N>) getRevisionMetadata(revisionEntities.get(entry.getKey())), //
                        entry.getValue())) //
                .sorted() //
                .collect(StreamUtils.toUnmodifiableList());
    }

    /**
     * Returns the {@link RevisionMetadata} wrapper depending on the type of the given object.
     *
     * @param object
     * @return
     */
    private RevisionMetadata<?> getRevisionMetadata(Object object) {

        return object instanceof DefaultRevisionEntity //
                ? new DefaultRevisionMetadata((DefaultRevisionEntity) object) //
                : new AnnotationRevisionMetadata<N>(object, RevisionNumber.class, RevisionTimestamp.class);
    }

    private static int toInt(long value) {

        if (value > Integer.MAX_VALUE) {
            throw new IllegalStateException(String.format("%s can't be mapped to an integer, too large!", value));
        }

        return Long.valueOf(value).intValue();
    }
}

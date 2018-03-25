package com.example.member.util.envers;

import org.hibernate.envers.DefaultRevisionEntity;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.envers.repository.support.ReflectionRevisionEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.history.support.RevisionEntityInformation;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class QuerydslEnversRevisionRepositoryFactoryBean<T extends QuerydslEnversRepositoryImpl<S, ID, N>, S, ID extends Serializable ,N extends Number & Comparable<N>> extends EnversRevisionRepositoryFactoryBean<T, S, ID, N> {

    private Class<?> revisionEntityClass;

    @SuppressWarnings("unchecked")
    public QuerydslEnversRevisionRepositoryFactoryBean(Class repositoryInterface) {
        super(repositoryInterface);
    }

    /**
     * Configures the revision entity class. Will default to {@link DefaultRevisionEntity}.
     *
     * @param revisionEntityClass
     */
    @Override
    public void setRevisionEntityClass(Class revisionEntityClass) {
        this.revisionEntityClass = revisionEntityClass;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean#createRepositoryFactory(javax.persistence.EntityManager)
     */
    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new RevisionRepositoryFactory<T, ID, N>(entityManager, revisionEntityClass);
    }

    /**
     * Repository factory creating {@link RevisionRepository} instances.
     *
     * @author Oliver Gierke
     */
    private static class RevisionRepositoryFactory<T, ID extends Serializable, N extends Number & Comparable<N>> extends JpaRepositoryFactory {

        private final RevisionEntityInformation revisionEntityInformation;
        private final EntityManager entityManager;

        /**
         * Creates a new {@link EnversRevisionRepositoryFactoryBean.RevisionRepositoryFactory} using the given {@link EntityManager} and revision entity class.
         *
         * @param entityManager must not be {@literal null}.
         * @param revisionEntityClass can be {@literal null}, will default to {@link DefaultRevisionEntity}.
         */
        public RevisionRepositoryFactory(EntityManager entityManager, Class<?> revisionEntityClass) {

            super(entityManager);
            this.entityManager = entityManager;
            revisionEntityClass = revisionEntityClass == null ? DefaultRevisionEntity.class : revisionEntityClass;
            this.revisionEntityInformation = DefaultRevisionEntity.class.equals(revisionEntityClass)
                    ? new DefaultRevisionEntityInformation() : new ReflectionRevisionEntityInformation(revisionEntityClass);
        }

        /*
         * (non-Javadoc)
         * @see org.springframework.data.jpa.repository.support.JpaRepositoryFactory#getTargetRepository(org.springframework.data.repository.core.RepositoryMetadata, javax.persistence.EntityManager)
         */
        @Override
        @SuppressWarnings({"unchecked", "rawtypes"})
        protected QuerydslEnversRepositoryImpl getTargetRepository(RepositoryInformation information) {

            return new QuerydslEnversRepositoryImpl(getEntityInformation(information.getDomainType()), revisionEntityInformation, entityManager);
        }

        /*
         * (non-Javadoc)
         * @see org.springframework.data.jpa.repository.support.JpaRepositoryFactory#getRepositoryBaseClass(org.springframework.data.repository.core.RepositoryMetadata)
         */
        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return QuerydslEnversRepositoryImpl.class;
        }

        /*
         * (non-Javadoc)
         * @see org.springframework.data.repository.core.support.RepositoryFactorySupport#getRepository(java.lang.Class, java.lang.Object)
         */
        @Override
        public <T> T getRepository(Class<T> repositoryInterface, Object customImplementation) {

            if (RevisionRepository.class.isAssignableFrom(repositoryInterface)) {

                Class<?>[] typeArguments = GenericTypeResolver.resolveTypeArguments(repositoryInterface,
                        RevisionRepository.class);
                Class<?> revisionNumberType = typeArguments[2];

                if (revisionEntityInformation.getRevisionNumberType().isPrimitive()) {
                    //revisionEntityInformation.getRevisionNumberType().
                    if (!revisionNumberType.getSimpleName().toLowerCase().startsWith(revisionEntityInformation.getRevisionNumberType().getName())) {
                        throw new IllegalStateException(String.format(
                                "Configured a revision entity type of %s with a revision type of %s "
                                        + "but the repository interface is typed to a revision type of %s!",
                                repositoryInterface,
                                revisionEntityInformation.getRevisionNumberType(), revisionNumberType));
                    }
                } else {
                    if (!revisionEntityInformation.getRevisionNumberType().equals(revisionNumberType)) {
                        throw new IllegalStateException(String.format(
                                "Configured a revision entity type of %s with a revision type of %s "
                                        + "but the repository interface is typed to a revision type of %s!",
                                repositoryInterface,
                                revisionEntityInformation.getRevisionNumberType(), revisionNumberType));
                    }
                }

            }

            return super.getRepository(repositoryInterface, customImplementation);
        }
    }
}

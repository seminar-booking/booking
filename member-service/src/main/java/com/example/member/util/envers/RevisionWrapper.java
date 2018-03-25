package com.example.member.util.envers;

import com.fasterxml.jackson.annotation.JsonFilter;
import org.springframework.data.history.Revision;

import java.time.LocalDateTime;

public class RevisionWrapper<N extends Number & Comparable<N>, T> {

    private N revisionNumber;

    private LocalDateTime revisionDate;

    @JsonFilter("deletedReferenceFilter")
    private T entity;

    public RevisionWrapper(Revision<N, T> revision) {

        this.revisionNumber = revision.getRequiredRevisionNumber();
        this.revisionDate = revision.getRequiredRevisionDate();
        this.entity = revision.getEntity();
    }

    public N getRevisionNumber() {
        return revisionNumber;
    }

    public LocalDateTime getRevisionDate() {
        return revisionDate;
    }

    public T getEntity() {
        return entity;
    }
}

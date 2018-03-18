package com.example.member.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "T_email_verification")
@EntityListeners(AuditingEntityListener.class)
public class EmailVerification {

    @Id
    private UUID id;

    @Column(nullable = false, length = 12)
    private String certificationLink = UUID.randomUUID().toString().substring(24);

    @Column(nullable = false)
    @CreatedDate
    private Date issuedAt;

    @OneToOne
    @MapsId
    @JsonBackReference
    private Member member;

    public EmailVerification() {

    }

    public EmailVerification(Member member) {
        this.member = member;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCertificationLink() {
        return certificationLink;
    }

    public void setCertificationLink(String certificationLink) {
        this.certificationLink = certificationLink;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailVerification that = (EmailVerification) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}

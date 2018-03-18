package com.example.member.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "T_member")
public class Member {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true, length = 100, updatable = false)
    private String email;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false, length = 20)
    private String name;

    @Column
    private boolean isVerified = false;

    @Column
    private short loginFailureCount = 0;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginDate;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedAt;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private TemporaryPassword temporaryPassword;

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public short getLoginFailureCount() {
        return loginFailureCount;
    }

    public void setLoginFailureCount(short loginFailureCount) {
        this.loginFailureCount = loginFailureCount;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(Date lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public TemporaryPassword getTemporaryPassword() {
        return temporaryPassword;
    }

    public void setTemporaryPassword(TemporaryPassword temporaryPassword) {
        this.temporaryPassword = temporaryPassword;
    }

    public boolean isBlocked() {
        return this.loginFailureCount >= 5;
    }

    public Member failLogin() {
        this.loginFailureCount++;
        return this;
    }

    public Member successLogin() {
        this.loginFailureCount = 0;
        this.lastLoginDate = new Date();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        return id != null ? id.equals(member.id) : member.id == null;
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

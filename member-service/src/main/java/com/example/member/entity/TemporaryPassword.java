package com.example.member.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "T_temporary_password")
public class TemporaryPassword {

    @Id
    private UUID id;

    @Column(nullable = false, length = 12)
    private String password;

    @Column(nullable = false)
    private Date expireAt;

    @OneToOne
    @MapsId
    private Member member;

    public TemporaryPassword() {
    }

    public TemporaryPassword(Member member) {
        this.member = member;
    }

    @PrePersist
    public void init() {
        password = UUID.randomUUID().toString().substring(26);
        expireAt = new Date(new Date().getTime() + 60000 * 3);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Date expireAt) {
        this.expireAt = expireAt;
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

        TemporaryPassword that = (TemporaryPassword) o;

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

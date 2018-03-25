package com.example.member.security;

import com.example.member.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.UUID;

public class UserAccount extends User {

    private UUID id;
    private String nickname;

    public UserAccount(Member member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getEmail(), member.getLoginPassword(),
                member.isVerified(), true, true,
                !member.isBlocked(), authorities);

        this.id = member.getId();
        this.nickname = member.getName();
    }

    public UUID getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }
}

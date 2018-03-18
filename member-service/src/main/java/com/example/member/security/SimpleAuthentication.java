package com.example.member.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;

public class SimpleAuthentication extends UsernamePasswordAuthenticationToken {

    private UUID id;
    private String nickname;

    public SimpleAuthentication(UserAccount account) {
        super(account.getUsername(), account.getPassword(), account.getAuthorities());
        this.id = account.getId();
        this.nickname = account.getNickname();
    }

    public SimpleAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public SimpleAuthentication(Object principal, Object credentials, UUID id, String name, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.id = id;
        this.nickname = name;
    }

    public UUID getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }
}

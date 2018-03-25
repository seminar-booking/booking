package com.example.member.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

    private PasswordEncoder passwordEncoder;

    public boolean matches(String password, String oldpassword) {

        return passwordEncoder.matches(password, oldpassword);
    }

    public String encode(String password) {

        return passwordEncoder.encode(password);
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}

package com.example.member.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SimpleAuthenticationProvider extends DaoAuthenticationProvider {

    @Override
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {

        SimpleAuthentication auth = new SimpleAuthentication((UserAccount)user);
        auth.setDetails(authentication.getDetails());

        return auth;
    }

    @Autowired
    public void setUserDetailsService(SimpleUserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
    }

    @Autowired
    @Override
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        super.setPasswordEncoder(passwordEncoder);
    }
}

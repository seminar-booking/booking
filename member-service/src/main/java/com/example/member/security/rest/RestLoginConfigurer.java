package com.example.member.security.rest;

import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

@Component
public class RestLoginConfigurer<T extends HttpSecurityBuilder<T>>
        extends AbstractAuthenticationFilterConfigurer<T, RestLoginConfigurer<T>, RestLoginParameterFilter> {

    public RestLoginConfigurer() {
        super(new RestLoginParameterFilter(), "/login");
    }

    public RestLoginConfigurer<T> usernameParameter(String parameter) {
        getAuthenticationFilter().setUsernameParameter(parameter);
        return this;
    }

    public RestLoginConfigurer<T> passwordParameter(String parameter) {
        getAuthenticationFilter().setPasswordParameter(parameter);
        return this;
    }

    @Override
    protected RequestMatcher createLoginProcessingUrlMatcher(String s) {
        return new AntPathRequestMatcher(s, "POST");
    }
}

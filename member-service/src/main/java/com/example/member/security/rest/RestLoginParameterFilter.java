package com.example.member.security.rest;

import com.example.member.security.SimpleAuthentication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class RestLoginParameterFilter extends UsernamePasswordAuthenticationFilter {

    private ObjectMapper objectMapper;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest servletRequest, HttpServletResponse response) throws AuthenticationException {

        if (!servletRequest.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + servletRequest.getMethod());
        }

        try {
            SimpleAuthentication authRequest = getUsernamePasswordToken(servletRequest);
            setDetails(servletRequest, authRequest);

            return this.getAuthenticationManager().authenticate(authRequest);
        } catch (IOException e) {
            throw new BadCredentialsException("Invalid Login Request");
        }
    }

    private SimpleAuthentication getUsernamePasswordToken(HttpServletRequest request) throws IOException {

        @SuppressWarnings("unchecked")
        Map<String, String> loginRequestMap = objectMapper.readValue(request.getInputStream(), HashMap.class);
        request.setAttribute("email", loginRequestMap.get(this.getUsernameParameter()));

        return new SimpleAuthentication(loginRequestMap.get(this.getUsernameParameter()), loginRequestMap.get(this.getPasswordParameter()));


    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}

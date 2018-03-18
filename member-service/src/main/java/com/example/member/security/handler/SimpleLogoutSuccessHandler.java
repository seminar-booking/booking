package com.example.member.security.handler;

import org.springframework.http.HttpStatus;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class SimpleLogoutSuccessHandler extends HttpStatusReturningLogoutSuccessHandler {

    public SimpleLogoutSuccessHandler() {
        super(HttpStatus.OK);
    }
}

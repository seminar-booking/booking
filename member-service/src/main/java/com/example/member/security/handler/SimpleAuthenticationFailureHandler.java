package com.example.member.security.handler;

import com.example.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class SimpleAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private MemberRepository memberRepository;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();

        if (exception instanceof BadCredentialsException) {
            logger.info(exception);
            //memberRepository.findOneByEmail(exception.)
        } else if (exception instanceof DisabledException) {
            writer.write("Account Not Verified");
        } else if (exception instanceof LockedException) {
            writer.write("This Account was Locked");
        }

        writer.flush();
        writer.close();
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
}

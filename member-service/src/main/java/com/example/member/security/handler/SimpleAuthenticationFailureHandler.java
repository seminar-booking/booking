package com.example.member.security.handler;

import com.example.member.repository.MemberRepository;
import com.example.member.util.ScalaResultWrapper;
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
            memberRepository.findOneByEmail(request.getAttribute("email").toString())
                    .ifPresent(member -> memberRepository.save(member.failLogin()));

            writer.write(new ScalaResultWrapper<>("error", "Email or Password is invalid").toString());
        } else if (exception instanceof DisabledException) {
            writer.write(new ScalaResultWrapper<>("error", "Account Not Verified").toString());
        } else if (exception instanceof LockedException) {
            writer.write(new ScalaResultWrapper<>("error", "This Account was Locked").toString());
        }

        writer.flush();
        writer.close();
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
}

package com.example.member.security.handler;

import com.example.member.repository.MemberRepository;
import com.example.member.security.SimpleAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SimpleAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private MemberRepository memberRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        memberRepository.findById(((SimpleAuthentication)authentication).getId())
                .map(member -> memberRepository.save(member.successLogin()));
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
}

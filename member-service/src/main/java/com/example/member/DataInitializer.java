package com.example.member;

import com.example.member.entity.Member;
import com.example.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@DependsOn("dataSource")
public class DataInitializer {

    private MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {

        if (memberRepository.count() == 0) {

            Member defaultUser = new Member();
            defaultUser.setEmail("test@example.com");
            defaultUser.setPassword(passwordEncoder.encode("TestPassword"));
            defaultUser.setName("Test");
            defaultUser.setVerified(true);
            memberRepository.save(defaultUser);
        }
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}

package com.example.member;

import com.example.member.entity.Member;
import com.example.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@DependsOn("dataSource")
public class DataInitializer {

    private MemberService memberService;

    @PostConstruct
    public void init() {

        Member defaultUser = new Member();
        defaultUser.setEmail("test@example.com");
        defaultUser.setPassword("TestPassword");
        defaultUser.setName("Test");
        defaultUser.setVerified(true);
        memberService.join(defaultUser);
    }

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
}

package com.example.member.resource;

import com.example.member.entity.Member;
import com.example.member.security.SimpleAuthentication;
import com.example.member.service.MemberService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class MemberResource {

    private MemberService memberService;

    @GetMapping(path = "/members")
    public Page<Member> getMemberList(@QuerydslPredicate(root = Member.class) Predicate predicate, Pageable page) {
        return memberService.findAll(predicate, page);
    }

    @GetMapping(path = "/member/myInfo")
    public Member getMyInfo(SimpleAuthentication authentication) {
        return memberService.findById(authentication.getId());
    }

    @PostMapping(path = "/member")
    @ResponseStatus(HttpStatus.CREATED)
    public void joinMember(Member member) {
        memberService.join(member);
    }

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
}

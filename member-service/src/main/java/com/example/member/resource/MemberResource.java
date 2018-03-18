package com.example.member.resource;

import com.example.member.entity.Member;
import com.example.member.repository.MemberRepository;
import com.example.member.security.SimpleAuthentication;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class MemberResource {

    private MemberRepository memberRepository;

    @GetMapping(path = "/members")
    public Page<Member> getMemberList(@QuerydslPredicate(root = Member.class) Predicate predicate, Pageable page) {
        return memberRepository.findAll(predicate, page);
    }

    @GetMapping(path = "/member/myInfo")
    public Optional<Member> getMyInfo(SimpleAuthentication authentication) {
        return memberRepository.findById(authentication.getId());
    }

    @GetMapping(path = "/member/verification/{key}")
    public String verifyMember(@PathVariable String key) {

        return null;
    }

    @PostMapping(path = "/member")
    @ResponseStatus(HttpStatus.CREATED)
    public void joinMember(Member member) {
        memberRepository.save(member);
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
}

package com.example.member.resource;

import com.example.member.entity.Member;
import com.example.member.security.SimpleAuthentication;
import com.example.member.service.MemberService;
import com.example.member.util.envers.RevisionWrapper;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public void joinMember(@RequestBody Member member) {
        memberService.join(member);
    }

    @PostMapping(path = "/member/{email}/verification")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void issueVerificationEmail(String email) {
        memberService.issueVerification(email);
    }

    @PostMapping(path = "/member/{email}/password")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void issueTemporaryPassword(String email) {
        memberService.issueTemporaryPassword(email);
    }

    @GetMapping(path = "/member/{id}/histories")
    public Page<RevisionWrapper<Integer, Member>> getMyInfo(@PathVariable UUID id, Pageable pageable) {
        return memberService.findMemberHistories(id, pageable)
                .map(RevisionWrapper::new);
    }

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
}

package com.example.member.service;

import com.example.member.entity.EmailVerification;
import com.example.member.entity.Member;
import com.example.member.mail.EmailDTO;
import com.example.member.mail.EmailService;
import com.example.member.mail.EmailType;
import com.example.member.repository.EmailVerificationRepository;
import com.example.member.repository.MemberRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class MemberService {

    private MemberRepository memberRepository;
    private EmailVerificationRepository emailVerificationRepository;
    private EmailService emailService;

    public void join(Member member) {

        memberRepository.save(member);

        Map<String, Object> emailVariables = new HashMap<>();
        emailVariables.put("nickname", member.getName());
        emailVariables.put("link", "http://localhost:12001/member/verification/" + member.getEmailVerification().getCertificationLink());

        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setAddressToSend(member.getEmail());
        emailDTO.setEmailType(EmailType.VERIFICATION);
        emailDTO.setVariables(emailVariables);
        emailService.send(emailDTO);
    }

    public Page<Member> findAll(Predicate predicate, Pageable page) {
        return memberRepository.findAll(predicate, page);
    }

    public Member findById(UUID id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member Not Found"));
    }

    public Member verify(String key) {
        return emailVerificationRepository.findByCertificationLink(key)
                .map(EmailVerification::getMember)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Certification Link"));
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setEmailVerificationRepository(EmailVerificationRepository emailVerificationRepository) {
        this.emailVerificationRepository = emailVerificationRepository;
    }

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
}

package com.example.member.service;

import com.example.member.entity.EmailVerification;
import com.example.member.entity.Member;
import com.example.member.mail.EmailDTO;
import com.example.member.mail.EmailService;
import com.example.member.mail.EmailType;
import com.example.member.repository.EmailVerificationRepository;
import com.example.member.repository.MemberRepository;
import com.example.member.util.PasswordUtil;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class MemberService {

    private PasswordUtil passwordUtil;
    private MemberRepository memberRepository;
    private EmailVerificationRepository emailVerificationRepository;
    private EmailService emailService;

    public void join(Member member) {

        if (memberRepository.findOneByEmail(member.getEmail()).isPresent()) {
            throw new DuplicateKeyException(String.format("%s already Joined", member.getEmail()));
        }

        member.setPassword(passwordUtil.encode(member.getPassword()));
        memberRepository.save(member);

        this.sendVerificationEmail(member);
    }

    public Page<Member> findAll(Predicate predicate, Pageable page) {
        return memberRepository.findAll(predicate, page);
    }

    public Member findById(UUID id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member Not Found"));
    }

    public void issueVerification(String email) {

        Member member = memberRepository.findOneByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Member Not Found"));

        member.setEmailVerification(new EmailVerification(member));
        memberRepository.save(member);

        this.sendVerificationEmail(member);
    }

    public void issueTemporaryPassword(String email) {

        Member member = memberRepository.findOneByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Member Not Found"));

        member.issueTemporaryPassword();
        memberRepository.save(member);

        this.sendTemporaryPasswordEmail(member);
    }

    public Member verify(String key) {
        return emailVerificationRepository.findByCertificationLink(key)
                .map(EmailVerification::getMember)
                .map(member -> {
                    member.setVerified(true);
                    return memberRepository.save(member);
                })
                .orElseThrow(() -> new IllegalArgumentException("Invalid Certification Link"));
    }

    public Page<Revision<Integer, Member>> findMemberHistories(UUID id, Pageable pageable) {

        return memberRepository.findRevisions(id, pageable);
    }

    private void sendVerificationEmail(Member member) {

        Map<String, Object> emailVariables = new HashMap<>();
        emailVariables.put("nickname", member.getName());
        emailVariables.put("link", "http://localhost:12001/member/verification/" + member.getEmailVerification().getCertificationLink());

        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setAddressToSend(member.getEmail());
        emailDTO.setEmailType(EmailType.VERIFICATION);
        emailDTO.setVariables(emailVariables);
        emailService.send(emailDTO);
    }

    private void sendTemporaryPasswordEmail(Member member) {

        Map<String, Object> emailVariables = new HashMap<>();
        emailVariables.put("nickname", member.getName());
        emailVariables.put("temporaryPassword", member.getTemporaryPassword().getPassword());

        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setAddressToSend(member.getEmail());
        emailDTO.setEmailType(EmailType.TEMP_PASSWORD);
        emailDTO.setVariables(emailVariables);
        emailService.send(emailDTO);
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

    @Autowired
    public void setPasswordUtil(PasswordUtil passwordUtil) {
        this.passwordUtil = passwordUtil;
    }
}

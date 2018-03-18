package com.example.member.controller;

import com.example.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MemberController {

    private MemberService memberService;

    @GetMapping(path = "/member/verification/{key}")
    public String verifyMember(@PathVariable String key, Model model) {

        model.addAttribute("name", memberService.verify(key).getName());
        return "accountVerified";
    }

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
}

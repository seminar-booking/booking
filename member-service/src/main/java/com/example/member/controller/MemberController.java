package com.example.member.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.member.dto.Member;

@RestController
@RequestMapping("/member")
public class MemberController {
	
	@PostMapping(produces = "application/json", consumes = "application/json")
	public void insertMemberInfo(Member memberInfo) {
		// 회원 가입 요청을 처리하기 위한 컨트롤러
		
		// 스프링 시큐리티에서 지원해주는 BCryptPasswordEncoder를 이용해 회원 비밀번호를 암호화
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		memberInfo.setPassword(passwordEncoder.encode(memberInfo.getPassword()));
		
	}
	
	@PutMapping(produces = "application/json", consumes = "application/json")
	public void updateMemberInfo(Member memberInfo) {
		// 회원 정보 변경 요청 처리 컨트롤러 
		// 비밀번호 변경에 관한 요청을 처리하는 별도 컨트롤러가 필요한지 의문임 
	}
	
	@GetMapping(path = "tempPassword", produces = "application/json", consumes = "application/json")
	public void getTempPassword(String email) {
		// 임시 비밀번호 발급에 대한 응답 컨트롤러 
	}
	
	@DeleteMapping(produces = "application/json", consumes = "application/json")
	public void deleteMemberInfo() {
		// 회원 탈퇴 요청을 처리하기 위한 컨트롤러 
		
		// 회원정보를 파라미터로 받아서는 안된다 
		// spring security의 authentication 사용 
	}
	
	@GetMapping(path = "memberInfo", produces = "application/json")
	public Member getmemberInfo() {
		// 회원정보를 파라미터로 받아서는 안된다 
		// spring security의 authentication 사용 
		return null;
	}
	
	@ResponseBody
	@PostMapping(path="/login", produces = "application/json")
	public String loginMember(@AuthenticationPrincipal Member member) {
		// login 요청을 처리하기 위한 컨트롤러 
		
		return null;
	}
//	
//	@RequestMapping(value="logout", method = RequestMethod.DELETE, produces = "application/json")
//	public String logoutMember(String token) {
//		// logout 요청을 처리하기 위한 컨트롤러 
//		// token 정보만 받으면 될 것으로 사료됨 
//		return null; 
//	}
	
	
}

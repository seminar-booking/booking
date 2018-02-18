package com.example.member.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.member.dto.Member;

@RestController
@RequestMapping("/member")
public class MemberController {
	
	@PostMapping(produces = "application/json")
	public void insertMemberInfo(Member memberInfo) {
		// 회원 가입 요청을 처리하기 위한 컨트롤러
		// return은 success인지 fail 인지만 나타내면 될 것 같은데 그렇다면 String으로 return하면 될 것 같다
		// => HTTP Status로 나타내면 된다 => return은 void면 된다 
	}
	
	@PutMapping(produces = "application/json")
	public void updateMemberInfo(Member memberInfo) {
		// 회원 정보 변경 요청 처리 컨트롤러 
		// 비밀번호 변경에 관한 요청을 처리하는 별도 컨트롤러가 필요한지 의문임 
	}
	
	@GetMapping(path = "/tempPassword", produces = "application/json")
	public void getTempPassword(String email) {
		// 임시 비밀번호 발급에 대한 응답 컨트롤러 
	}
	
	@DeleteMapping(path="/deleteMemberInfo", produces = "application/json")
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
	

//	@RequestMapping(value="/login", method = RequestMethod.GET, produces = "application/json")
//	public String loginMember(Member loginInfo) {
//		// login 요청을 처리하기 위한 컨트롤러 
//		// id, password로 login 이후 token을 return 혹은 로그인 실패를 return 해야 하는데 이때 token 정보는 생성 후  
//		// String으로 return 하면 되는지 잘 모르겠음; 아니라면 별도의 DTO를 만들어서 return해야 하나? 혹은 map에 String의 token
//		// 정보를 담아서 return 해야 하나? 
//		// 그렇다면 logout의 경우는 파라미터로 token을 받고 그 token을 만료 시키는 구성이 맞다고 생각하는데 
//		// 이때 받는 파라미터도 String으로 받으면 되는지 의문임, 따라서 학습 필요  
//		// JWT 인증 과정을 살펴 봤을때 요청 메서드를 POST로 하는 것을 확인 했는데 POST는 REST 방식의 경우 insert인 것으로 알고 있는데
//		// POST로 login 요청을 하는것이 맞는지도 궁금함 
//		
//		return null;
//	}
//	
//	@RequestMapping(value="logout", method = RequestMethod.DELETE, produces = "application/json")
//	public String logoutMember(String token) {
//		// logout 요청을 처리하기 위한 컨트롤러 
//		// token 정보만 받으면 될 것으로 사료됨 
//		return null; 
//	}
	
	
}

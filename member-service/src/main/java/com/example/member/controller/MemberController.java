package com.example.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.member.dto.Member;
import com.example.member.dto.TempPassword;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@RequestMapping(value = "/insertMember", method = RequestMethod.POST, produces = "application/json")
	public String insertMemberInfo(Member memberInfo) {
		// 회원 가입 요청을 처리하기 위한 컨트롤러
		// 네이밍을 insertMember 로 하는것이 맞는지 첫번째 의문, 일반적으로 사용하는 네이밍 룰을 잘 모르겠음 add? insert? 
		// return은 success인지 fail 인지만 나타내면 될 것 같은데 그렇다면 String으로 return하면 될 것 같다
		return null;
	}
	
	@RequestMapping(value= "/updateMemberInfo", method = RequestMethod.POST, produces = "application/json")
	public String updateMemberInfo(Member memberInfo) {
		// 회원 정보 변경 요청 처리 컨트롤러 
		// 비밀번호 변경에 관한 요청을 처리하는 별도 컨트롤러가 필요한지 의문임 
		// return은 success인지 fail 인지만 나타내면 될 것 같은데 그렇다면 String으로 return하면 될 것 같다
		return null;
	}
	
	@RequestMapping(value = "/getTempPassword", method = RequestMethod.GET, produces = "application/json")
	public TempPassword getTempPassword(String email) {
		// 임시 비밀번호 발급에 대한 응답 컨트롤러 
		// 파라미터를 무엇을 받아야 할 지 의문임. id값을 받던 email을 받던 상관은 없는데 화면단에서 id값을 줄 수 있는지 잘 모르겠음
		// 확실히 줄 수 있는 정보는 email이라 판단되어 eamil을 파라미터로 받기로 함 
		// 의문은 email로 rdbms를 검색하게 되면 기껏 uuid를 설정한 의미가 있는지 의문임 => nosql db를 사용하면 좀 다른점이 있을지는 학습이 필요 
		return null;
	}
	
	@RequestMapping(value="/deleteMemberInfo", method = RequestMethod.DELETE, produces = "application/json")
	public String deleteMemberInfo(String email) {
		// 회원 탈퇴 요청을 처리하기 위한 컨트롤러 
		// 파라미터를 무엇을 받아야 할 지 의문임. id값을 받던 email을 받던 상관은 없는데 화면단에서 id값을 줄 수 있는지 잘 모르겠음
		// 확실히 줄 수 있는 정보는 email이라 판단되어 eamil을 파라미터로 받기로 함 
		// 의문은 email로 rdbms를 검색하게 되면 기껏 uuid를 설정한 의미가 있는지 의문임 
		return null;
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET, produces = "application/json")
	public String loginMember(Member loginInfo) {
		// login 요청을 처리하기 위한 컨트롤러 
		// id, password로 login 이후 token을 return 혹은 로그인 실패를 return 해야 하는데 이때 token 정보는 생성 후  
		// String으로 return 하면 되는지 잘 모르겠음; 아니라면 별도의 DTO를 만들어서 return해야 하나? 혹은 map에 String의 token
		// 정보를 담아서 return 해야 하나? 
		// 그렇다면 logout의 경우는 파라미터로 token을 받고 그 token을 만료 시키는 구성이 맞다고 생각하는데 
		// 이때 받는 파라미터도 String으로 받으면 되는지 의문임, 따라서 학습 필요  
		// JWT 인증 과정을 살펴 봤을때 요청 메서드를 POST로 하는 것을 확인 했는데 POST는 REST 방식의 경우 insert인 것으로 알고 있는데
		// POST로 login 요청을 하는것이 맞는지도 궁금함 
		
		return null;
	}
	
	@RequestMapping(value="logout", method = RequestMethod.DELETE, produces = "application/json")
	public String logoutMember(String token) {
		// logout 요청을 처리하기 위한 컨트롤러 
		// token 정보만 받으면 될 것으로 사료됨 
		return null; 
	}
	
	@RequestMapping(value = "/getMemberInfo", method = RequestMethod.GET, produces = "application/json")
	public Member getmemberInfo(int id) {
		// 회원 정보 요청에 관한 응답 컨트롤
		// 문제는 요청하는 회원 정보는 url에 나타나면 안될 것 같은데 get 요청으로 처리하는것이 맞는지 의문임 
		// 회원 정보를 요청하는 경우는 주로 reservation-service 일 것으로 판단 되는데 id값을 파라미터로 줄 수 있을지 잘 모르겠으나
		// 가능할 것이라 판단되어 id값을 요청하기로 함 
		return null;
	}
	
	
	
}

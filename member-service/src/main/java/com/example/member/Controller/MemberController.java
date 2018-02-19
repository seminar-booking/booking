package com.example.member.Controller;

import java.util.Collection;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.member.Member;

@RestController("/member")
public class MemberController {
	
					// produces 와 consumes를 공통으로 뺀다??? 어케??
	@PostMapping(path = "/info" , produces = "application/json" , consumes = "application/json")
	public HttpStatus insertMmemberInfo(Member member) {
		
		return HttpStatus.OK;
//		HttpStatus 를 리턴하는게 아닌 리턴은 void???  그렇담 httpstatus는 어디다 설정하는가?? 
		// Httpstatus를 헤더에 집어넣는 방법은??
	}
	
	@PutMapping(path = "/info" , produces = "application/json" , consumes = "application/json")
	public void updateMemberInfor(Member member) {
		// 회원에 대한 정보변경은 Authentication 보단 Member가 맞는듯.
		// 기존의 인증정보와 수정될 정보간의 비교할 데이터는 없을까??
		// Autentication 을 파라미터로 이용하려면?? security만 디펜던시에 넣어주면됨?? 기억이...
		
	}
	
	@DeleteMapping(path = "/info" , produces = "application/json" , consumes = "application/json")
	public void deleteMemberInfo(Authentication auth) {
		//회원탈퇴이지만 실제 작업은 update인데 REST Delete를 쓰는게 맞는것인가??
		
	}
	
	@GetMapping(path = "/info" , produces = "application/json" , consumes = "application/json")
	public Member selectMemberInforo(Long id) {
		//getMapping일 경우 id값? or Authentication??   URL에 id값이 노출되도 되는것인가??
		
		return null;
	}
	
	@GetMapping(path = "/infoList" , produces = "application/json" , consumes = "application/json")
	public Collection<?> selectMemberInforoList(Collection<?> memberParam) {
		//기간별 사용자리스트 or 특정조건의 사용자들을 조회하기위해
		//파라미터로 Collection을 이렇게 사용해도 되는것인가??
		
		return null;
	}
	
	@PostMapping(path = "/issueEmail" , produces = "application/json" , consumes = "application/json")
	public void issueTempPassword(String email) {
		//파라미터가 email하나. 이러할 경우도 consumes로 json타입의 파리미터 제한해야하는가??
		
	}
	
	@PutMapping(path = "/issueEmail" , produces = "application/json" , consumes = "application/json")
	public void certifiedEmail(String email) {
		//임시비밀번호와 이메일인증 둘다 Email을 이용하는것이니 도메인을 동일하게 하면 자원관리에 조금더 효율적???
		
	}
	
	@PutMapping(path = "/login" , produces = "application/json" , consumes = "application/json")
	public void login(Member member) {
		//토큰정보 생성 ,헤더에 토큰정보를 담음?? 어떻게??
	}

	@DeleteMapping(path = "/login" , produces = "application/json" , consumes = "application/json")
	public void logout(Authentication auth) {
		// REST  DELETE + /login ??  혹은 REST POST + /logout ?? 어떻게 하는것이 맞는것인가??

	}
	
}

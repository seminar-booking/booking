package com.example.reservationservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by KwangHan on 2018. 2. 9..
 */
@Controller("/reservation")
public class ReservationController {
	
	@GetMapping(value = { "/index", "" })
	public ModelAndView index() {
//		초기화면, 데이터 입력을 위한 화면이므로 달리 전달 받을 정보가 없다.
		return null;
	}
	
	@PostMapping(value = { "/boothList", "step1" })
	public ModelAndView boothList() {
//		추천 받은 부스 리스트를 보여주는 화면
//		이전 화면에서 선택한 정보를 가공하여 추천 부스를 뽑아야 하므로 POST
		return null;
	}
	
	@GetMapping(value = { "/facultyList", "step2" })
	public ModelAndView faculties() {
//		장비 리스트, 실제 개발시에서는 필요 없음
		return null;
	}
	
	@PostMapping(value = { "/preview", "step3" })
	public ModelAndView preview() {
//		사용자가 이전 스텝에서 선택한 정보를 보여주고, 추가로 필요한 정보를 입력받는다.
//		세션에 저장할 것이라면 Get 도 상관없음.
		return null;
	}
	
	@PostMapping(value = {"/process"})
	public ModelAndView process() {
//	    최종 승인이 날 시 프로세스 처리를 위한 메서드. 이후 메인 혹은 정해진 페이지로 리다이렉트한다.
//		예약 히스토리 란으로..?
		return null;
	}
}

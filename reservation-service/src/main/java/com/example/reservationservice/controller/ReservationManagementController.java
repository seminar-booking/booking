package com.example.reservationservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by KwangHan on 2018. 2. 9..
 */
@Controller("/reservation/management")
public class ReservationManagementController {

	@GetMapping("/history")
	public ModelAndView history() {
//		예약 history 를 제공한다.
		return null;
	}
	
	@GetMapping("/group")
	public ModelAndView group() {
//		예약 시 사용할 모임명 을 관리한다.
		return null;
	}
	
	@DeleteMapping("/group")
	public ModelAndView groupDelete() {
//		CRUD를 REST API 처럼 HTTP METHOD 로 분류..
		return null;
	}

}

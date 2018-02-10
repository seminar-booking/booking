package com.example.reservationservice.controller;

import com.example.reservationservice.dto.ReservationGroupHistory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by KwangHan on 2018. 2. 9..
 */
@Controller("/reservation/management")
public class ReservationManagementController {
	
	@GetMapping(value = "/history",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ReservationGroupHistory> history() {
//		예약 history 를 제공한다.
		return null;
	}
	
	@GetMapping(value = "/group",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ReservationGroupHistory group() {
//		예약 시 사용할 모임명 을 관리한다.
		return null;
	}
	
	@GetMapping(value = "/group/{groupNameId}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String groupDelete(@PathVariable(value = "groupNameId") long groupNameId) {
//		삭제할 대상의 id 정보가 필요하다.
//		history 화면으로의 redirect 할 예정이므로 어떤 데이터를 넘겨야될지 고민이 필요함
		return null;
	}
	
}

package com.example.reservationservice.controller;

import com.example.reservationservice.dto.PreviewData;
import com.example.reservationservice.dto.Room;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Created by KwangHan on 2018. 2. 9..
 */
@Controller("/reservation")
public class ReservationController {
	
	@GetMapping(value = { "/index", "" },
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Room> index() {
//		초기화면, 데이터 입력을 위한 화면이므로 달리 전달 받을 정보가 없다.
		return null;
	}
	
	@PostMapping(value = { "/boothList", "step1" },
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Room> roomList() {
//		추천 받은 부스 리스트를 보여주는 화면
//		이전 화면에서 선택한 정보를 가공하여 추천 부스를 뽑아야 하므로 POST
		return null;
	}
	
	@PostMapping(value = { "/preview", "step3" },
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PreviewData preview() {
//		사용자가 이전 스텝에서 선택한 정보를 보여주고, 추가로 필요한 정보를 입력받는다.
//		세션에 저장할 것이라면 Get 도 상관없음.
		return null;
	}
	
	@PostMapping(value = { "/process" },
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String process() {
//	    Page redirect 라 어떤식으로 데이터를 넘겨야될지 좀더 고민이 필요
//		예약 히스토리 란으로..?
		return null;
	}
}

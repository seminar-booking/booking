package com.example.reservationservice.controller;

import com.example.reservationservice.dto.Reservation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

/**
 * Created by KwangHan on 2018. 2. 9..
 */
@RestController
@RequestMapping(path = "/reservation",
		produces = MediaType.APPLICATION_JSON_VALUE
)
public class ReservationController {
	
	
	// produce : content-type, consumes = accept
	@GetMapping
	public Collection<Reservation> getReservations() {
		System.out.println(UUID.randomUUID().toString());
		
		Reservation reservation = new Reservation();
		reservation.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		reservation.setEndDate(new Timestamp(System.currentTimeMillis()));
		reservation.setStartDate(new Timestamp(System.currentTimeMillis()));
		reservation.setId(UUID.randomUUID());
		reservation.setRoomId(16);
		reservation.setGroupName("CEO Mr.Lo");
		reservation.setMemberCount(5);
		reservation.setStatus(Reservation.Status.ACTIVE);
		reservation.setUserId(500);
		
		System.out.println("-------");
		System.out.println(reservation);
		return new ArrayList<>();
	}
	
	@GetMapping(path = "/{id}")
	public Reservation getReservation(@PathVariable UUID id) {
//		예약 시 사용할 모임명 을 관리한다.
		//486afe44-88a4-4d9f-9771-17dc06bc7a42
		return null;
	}
	
	@PostMapping
	public void generate(@RequestBody Reservation reservation) {
//		가공된 멤버 데이터가 필요하다. > 현재 dependency 가 안잡혀 있어서 아무개 멤버로 표시ㅎㅎ
	
	}
	
	@DeleteMapping(path = { "/{id}" })
	public void cancel(@PathVariable UUID id) {
	
	}
	
	
}

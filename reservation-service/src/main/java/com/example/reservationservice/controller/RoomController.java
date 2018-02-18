package com.example.reservationservice.controller;

import com.example.reservationservice.dto.Room;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by KwangHan on 2018. 2. 9..
 */
@RestController
@RequestMapping(path = "/room",
		produces = MediaType.APPLICATION_JSON_VALUE
		)
public class RoomController {
	
	@GetMapping(path = "/{id}")
	public Room getRoom(@PathVariable long id) {
//		선택한 방에 대한 정보를 제공한다.
		return new Room();
	}
	
	@GetMapping
	public Collection<Room> getAvailableRooms() {
//		추천 받은 부스 리스트를 보여주는 화면, Reservation 의 정보가 필요하다....
//		reservationService.getDailyReservations >> return available rooms
		Room room = new Room();
		room.setId(123);
		room.setOptimalPersonnel(4);
		room.setName("C type room");
		room.setMinimumPersonnel(6);
		room.setMinimumPersonnel(4);
		room.setDescription("Test Room");
		room.setStatus(Room.Status.AVAILABLE);
		System.out.println(room);
		return new ArrayList<Room>() {{
			add(new Room());
			add(room);
		}};
	}
	
	@PostMapping
	public void generate(@RequestBody Room room) {
		// Http Status 로 관리
		// 200, 500 ....
	}
	
}

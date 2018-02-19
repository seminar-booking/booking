package com.example.reservationservice.controller;


import com.example.reservationservice.dto.Reservation;
import com.example.reservationservice.dto.Room;
import com.example.reservationservice.tempdata.InitData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(path = "/reservation",
				produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationController {

	@Autowired
	InitData initData;

    @GetMapping(path = "/rooms")
    public List<Room> returnAllRooms() {
        // method naming : 방 모두를 반환한다는 의미, (return, give)등의 동사 중 어떤 걸 써야 할 지 고민.
        // 지금 현재 선택 가능한 모든 방을 반환하여 출력.

        return initData.getRoomList();
    }

    @GetMapping(path = "/optimum")
    public List<Room> returnAvailableRooms() {
        // method naming : 이용 가능한 방을 반환한다는 의미.
        // 요건에 따라 최적의 방을 검색한 뒤, 적절한 방을 반환.

		List<Room> availableRoomList = new ArrayList<>();

		for (Room item : initData.getRoomList()){
			if(item.getOptimalPersonnel() >= 3){
				availableRoomList.add(item);
			}
		}
        return availableRoomList;
    }

    @PostMapping(path = "/{userId}")
    public ResponseEntity registerReservation(@PathVariable int userId) {
		// method naming : 예약을 등록한다는 의미.
		// 해당 member id에 대해 예약 정보를 등록.
		// 정상적으로 등록 되었는 지, 아닌지 여부를 반환.
		// 각 return 상태 값은 로직의 결과에 따라 달라짐.


		Reservation reservation = new Reservation();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    	reservation.setId(UUID.randomUUID());
    	reservation.setRoomId(1);
    	reservation.setUserId(userId);
    	reservation.setGroupName("group");
    	reservation.setMemberCount(2);
    	reservation.setStatus(Reservation.Status.ACTIVE);
    	reservation.setCreatedDate(timestamp);
    	reservation.setStartDate(timestamp);
    	reservation.setEndDate(timestamp);

		initData.setReservation(reservation);

		return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}/history")
    public List<Reservation> returnReservationHistory(@PathVariable int userId) {
        // method naming : 예약 내역을 반환한다는 의미.
        // 회원의 예약 내역을 출력.

        return initData.getReservationList();
    }
}

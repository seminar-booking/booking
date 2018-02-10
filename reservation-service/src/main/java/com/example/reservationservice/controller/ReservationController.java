package com.example.reservationservice.controller;


import com.example.reservationservice.dto.Reservation;
import com.example.reservationservice.dto.Room;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class ReservationController {


    @GetMapping(value = "/reservation/rooms")
    public List<Room> returnAllRooms() {
        // method naming : 방 모두를 반환한다는 의미, (return, give)등의 동사 중 어떤 걸 써야 할 지 고민.
        // 지금 현재 선택 가능한 모든 방을 반환하여 출력.
        return null;
    }

    @GetMapping(value = "/reservation/rooms/optimum")
    public List<Room> returnAvailableRooms() {
        // method naming : 이용 가능한 방을 반환한다는 의미.
        // 요건에 따라 최적의 방을 검색한 뒤, 적절한 방을 반환.
        return null;
    }

    @PostMapping(value = "/reservation/{userId}")
    public ResponseEntity registerReservation(@PathVariable int userId) {
        // method naming : 예약을 등록한다는 의미.
        // 해당 member id에 대해 예약 정보를 등록.
        // 정상적으로 등록 되었는 지, 아닌지 여부를 반환.
        // 각 return 상태 값은 로직의 결과에 따라 달라짐.
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/reservation/{userId}/history")
    public List<Reservation> returnReservationHistory(@PathVariable int userId) {
        // method naming : 예약 내역을 반환한다는 의미.
        // 회원의 예약 내역을 출력.
        return null;
    }
}

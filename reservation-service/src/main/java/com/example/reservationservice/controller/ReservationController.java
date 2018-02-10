package com.example.reservationservice.controller;


import com.example.reservationservice.dto.Room;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller("reservation")
public class ReservationController {


    @GetMapping(value = {"/room/all"})
    public List<Room> returnAllRoom() {
        // method naming : 방 모두를 반환한다는 의미, (return, give)등의 동사 중 어떤 걸 써야 할 지 고민.
        // 지금 현재 선택 가능한 모든 방을 반환하여 출력.
        return null;
    }

    @GetMapping(value = {"/room/optimum"})
    public List<Room> returnAvailableRoom() {
        // method naming : 이용 가능한 방을 반환한다는 의미.
        // 요건에 따라 최적의 방을 검색한 뒤, 적절한 방을 반환.
        return null;
    }

    @PostMapping(value = {"/reservation/{memberid}"})
    public boolean registerReservation() {
        // method naming : 예약을 등록한다는 의미.
        // 해당 member id에 대해 모든 예약 정보를 등록.
        // 정상적으로 등록 되었는 지, 아닌지 여부를 반환.
        return true;
    }
}

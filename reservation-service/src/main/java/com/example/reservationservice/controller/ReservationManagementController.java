package com.example.reservationservice.controller;

import com.example.reservationservice.dto.Reservation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller("/reservation/management")
public class ReservationManagementController {


    @GetMapping("/history/{memberid}")
    public List<Reservation> returnReservationHistory() {
        // method naming : 예약 내역을 반환한다는 의미.
        // 회원의 예약 내역을 출력.
        return null;
    }

    @GetMapping("/group/name/{memberid}")
    public List<String> returnGroupName() {
        // method naming : 그룹 이름을 반환한다는 의미.
        // URI를 group/name으로 한 이유는, 추 후 Group명 이외에 다른 기능 혹은 Resoure가 추가될 수 있다는 이유(예. 그룹의 주소록 등)로 계층으로 나눔.
        // member id를 통해 해당 member의 그룹 이름들을 출력.
        return null;
    }

    @DeleteMapping("/group/name/{name}")
    public boolean deleteGroupName() {
        // method naming : 그룹 이름을 삭제한다는 의미.
        // 그룹 이름(예약 모임 명) 을 삭제.
        // 정상적으로 삭제 되었는 지, 아닌지 여부를 반환.
        return true;
    }
}


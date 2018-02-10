package com.example.reservationservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("/reservation/management")
public class ReservationManagementController {


    @GetMapping("/history")
    public ModelAndView printHistory() {
        // 회원의 예약 내역을 출력해주는 페이지.
        return null;
    }


    @GetMapping("/groupname")
    public ModelAndView printGroupName() {
        // 그룸 이름(예약 모임 명) 을 출력해주는 페이지.
        return null;
    }

    @DeleteMapping("/groupname")
    public ModelAndView deleteGroupName() {
        // 그룸 이름(예약 모임 명) 을 삭제하는 내부 페이지.
        return null;
    }
}


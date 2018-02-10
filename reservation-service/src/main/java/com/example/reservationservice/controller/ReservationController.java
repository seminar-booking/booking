package com.example.reservationservice.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller("reservation")
public class ReservationController {


    @GetMapping(value = {"/index"})
    public ModelAndView printIndex() {
        // 부스 list 출력.
        // 별 다른 데이터를 입력하지 않아도 되는 페이지이므로 Get방식이 적합할 것 같다.
        return null;
    }

    @PostMapping(value = {"/boothselect"})
    public ModelAndView selectAvailableBooth() {
        // 검색 결과에 대한 부스 list 출력.
        // 앞선 메인 페이지에서 선택한 결과(단기/장기 포함)로 최적인 방을 찾아야 하므로 Post로 해야 할 필요가 있다.
        return null;
    }

    @PostMapping(value = {"/confirm"})
    public ModelAndView confirmReservation() {
        // 모든 예약 정보 최종 확인 페이지 출력.
        // 핸드폰 번호 인증 등의 이유로 Post가 적합할 것 같다.
        return null;
    }

    @PostMapping(value = {"/register"})
    public ModelAndView registerReservation() {
        // 모든 예약 정보를 등록하는 내부 페이지.
        return null;
    }
}

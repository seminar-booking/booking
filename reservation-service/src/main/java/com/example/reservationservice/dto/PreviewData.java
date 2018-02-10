package com.example.reservationservice.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.json.JSONObject;

/**
 * Created by KwangHan on 2018. 2. 10..
 */
public class PreviewData {
	private Reservation reservation;
	private JSONObject  member;
//	member 의 개인정보를 바탕으로 input tag 에 자동으로 value 를 세팅하는 부분이 있음
//	이 부분을 reservation method 에서 한방에 할지, 2번의 ajax를 할지 필요
//	reservation 에서 처리를 한다면 member 의 service 와 연동후 추가 수정이 필요할 수도.
	
	public Reservation getReservation() {
		return reservation;
	}
	
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	
	public JSONObject getMember() {
		return member;
	}
	
	public void setMember(JSONObject member) {
		this.member = member;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}

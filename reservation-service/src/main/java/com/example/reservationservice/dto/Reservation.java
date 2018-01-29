package com.example.reservationservice.dto;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by KwangHan on 2018. 1. 21..
 */
public class Reservation {
	
	private UUID id;
	private int  roomId;
	private int  userId;
	
	private String groupName;
	
	private int memberCount;
	
	private int status;
	
	private Timestamp createdDate;
	private Timestamp startTime;
	private Timestamp endTime;
	
	public enum Status {
		
		ACTIVE(1), USER_CANCEL(2), ADMIN_CANCEL(3);
		private int code;
		
		Status(int code) {
			this.code = code;
		}
		
		public int getCode() {
			return code;
		}
		
		public void setCode(int code) {
			this.code = code;
		}
	}
	
	public int getRoomId() {
		return roomId;
	}
	
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getGroupName() {
		return groupName;
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public int getMemberCount() {
		return memberCount;
	}
	
	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	
	public Timestamp getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	
	public Timestamp getEndTime() {
		return endTime;
	}
	
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	
	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
}

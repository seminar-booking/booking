package com.example.reservationservice.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
	
	private Status status;
	@JsonFormat
			(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Timestamp createdDate;
	@JsonFormat
			(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Timestamp startDate;
	@JsonFormat
			(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Timestamp endDate;

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Reservation that = (Reservation) o;

		if (roomId != that.roomId) return false;
		if (userId != that.userId) return false;
		if (memberCount != that.memberCount) return false;
		if (status != that.status) return false;
		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (groupName != null ? !groupName.equals(that.groupName) : that.groupName != null) return false;
		if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
		if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
		return endDate != null ? endDate.equals(that.endDate) : that.endDate == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + roomId;
		result = 31 * result + userId;
		result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
		result = 31 * result + memberCount;
		result = 31 * result + status.getCode();
		result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
		result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
		result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
		return result;
	}
}

package com.example.reservationservice.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by KwangHan on 2018. 1. 21..
 */
public class Room {
	private long id;
	private String name; /* name, 방번호 퉁침 */
	private String description;
	private int optimalPersonnel;
	private int minimumPersonnel;
	private int maximumPersonnel;
	//@Enumerable
	//private Status status;
	private int status;
	
	public enum Status {
		
		AVAILABLE(1), UNAVAILABLE(0);
		int code;
		
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
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getOptimalPersonnel() {
		return optimalPersonnel;
	}
	
	public void setOptimalPersonnel(int optimalPersonnel) {
		this.optimalPersonnel = optimalPersonnel;
	}
	
	public int getMinimumPersonnel() {
		return minimumPersonnel;
	}
	
	public void setMinimumPersonnel(int minimumPersonnel) {
		this.minimumPersonnel = minimumPersonnel;
	}
	
	public int getMaximumPersonnel() {
		return maximumPersonnel;
	}
	
	public void setMaximumPersonnel(int maximumPersonnel) {
		this.maximumPersonnel = maximumPersonnel;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", id)
				.append("name", name)
				.append("description", description)
				.append("optimalPersonnel", optimalPersonnel)
				.append("minimumPersonnel", minimumPersonnel)
				.append("maximumPersonnel", maximumPersonnel)
				.append("status", status)
				.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Room room = (Room) o;
		
		if (id != room.id) return false;
		if (optimalPersonnel != room.optimalPersonnel) return false;
		if (minimumPersonnel != room.minimumPersonnel) return false;
		if (maximumPersonnel != room.maximumPersonnel) return false;
		if (status != room.status) return false;
		if (name != null ? !name.equals(room.name) : room.name != null) return false;
		return description != null ? description.equals(room.description) : room.description == null;
	}
	
	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + optimalPersonnel;
		result = 31 * result + minimumPersonnel;
		result = 31 * result + maximumPersonnel;
		result = 31 * result + status;
		return result;
	}
}

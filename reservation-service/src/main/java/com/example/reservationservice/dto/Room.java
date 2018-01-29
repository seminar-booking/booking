package com.example.reservationservice.dto;

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
		return "Room{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", optimalPersonnel=" + optimalPersonnel +
				", minimumPersonnel=" + minimumPersonnel +
				", maximumPersonnel=" + maximumPersonnel +
				", status=" + status +
				'}';
	}
}

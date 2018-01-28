package com.example.member;

public class TempPassword {
	
	private String id;
	private String temPassword;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTemPasswd() {
		return temPassword;
	}
	public void setTemPasswd(String temPasswd) {
		this.temPassword = temPasswd;
	}
	
	@Override
	public String toString() {
		return "TempPassword [id=" + id + ", temPasswd=" + temPassword + "]";
	}
	

}

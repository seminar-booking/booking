package com.example.member;

public class EmailVelified {
	
	private String id;
	private String email;
	private String certificationNumber;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCertificationNumber() {
		return certificationNumber;
	}
	public void setCertificationNumber(String certificationNumber) {
		this.certificationNumber = certificationNumber;
	}
	
	@Override
	public String toString() {
		return "EmailVelified [id=" + id + ", email=" + email + ", certificationNumber=" + certificationNumber + "]";
	}

}

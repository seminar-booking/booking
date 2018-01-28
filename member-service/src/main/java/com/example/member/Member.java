package com.example.member;
import java.util.Date;

/**
 * @author chulmac
 *
 */
public class Member {
	private String id;
	private String email;
	private String password;
	private String name;
	private String phone;
	private short loginFailCnt;
	private Date lastLoginDate;  //YYYY-MM-DD 24HH:MI:SI
	private boolean isVelified;
	private Date createDate;
	private Date updateDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Short getLoginFailCnt() {
		return loginFailCnt;
	}
	public void setLoginFailCnt(Short loginFailCnt) {
		this.loginFailCnt = loginFailCnt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhohe() {
		return phone;
	}
	public void setPhohe(String phohe) {
		this.phone = phohe;
	}
	
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public boolean isUseYn() {
		return isVelified;
	}
	public void setUseYn(boolean useYn) {
		this.isVelified = useYn;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "Member [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", phone="
				+ phone + ", loginFailCnt=" + loginFailCnt + ", lastLoginDate=" + lastLoginDate + ", isValified="
				+ isVelified + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
	
	
	
}

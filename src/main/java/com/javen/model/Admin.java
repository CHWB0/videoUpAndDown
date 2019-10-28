package com.javen.model;

public class Admin {
	private Integer id;
	private Integer aNo;
	private String adminName;
	private String aPassword;
	private String aType;
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin(Integer aNo, String adminName, String aPassword, String aType) {
		super();
		this.aNo = aNo;
		this.adminName = adminName;
		this.aPassword = aPassword;
		this.aType = aType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getaNo() {
		return aNo;
	}
	public void setaNo(Integer aNo) {
		this.aNo = aNo;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdmainName(String adminName) {
		this.adminName = adminName;
	}
	public String getaPassword() {
		return aPassword;
	}
	public void setaPassword(String aPassword) {
		this.aPassword = aPassword;
	}
	public String getaType() {
		return aType;
	}
	public void setaType(String aType) {
		this.aType = aType;
	}
	@Override
	public String toString() {
		return "Admin [aId=" + id + ", aNo=" + aNo + ", admainName=" + adminName + ", aPassword=" + aPassword
				+ ", aType=" + aType + "]";
	}
}

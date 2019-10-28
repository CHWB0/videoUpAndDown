package com.javen.model;

public class User {
	private Integer uId;
	private String uNo;
	private String userName;
    private String password;
    private Integer uType;
    
    public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String uNo,String userName, String password, Integer uType) {
		this.uNo = uNo;
		this.userName = userName;
		this.password = password;
		this.uType = uType;
	}
    public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getuType() {
		return uType;
	}
	public void setuType(Integer uType) {
		this.uType = uType;
	}
	public String getuNo() {
		return uNo;
	}
	public void setuNo(String uNo) {
		this.uNo = uNo;
	}
	@Override
	public String toString() {
		return "User [uId=" + uId + ", uNo=" + uNo + ", userName=" + userName + ", password=" + password + ", uType="
				+ uType + "]";
	}

}
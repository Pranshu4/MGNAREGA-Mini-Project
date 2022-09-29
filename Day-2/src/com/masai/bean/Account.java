package com.masai.bean;

public class Account {
	private String username;
	private int password;
	private String mobile;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	public Account(String username, int password, String mobile) {
		super();
		this.username = username;
		this.password = password;
		this.mobile = mobile;
	}
	
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", mobile=" + mobile + "]";
	}
	
}

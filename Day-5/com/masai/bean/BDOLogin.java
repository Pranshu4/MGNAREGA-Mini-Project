package com.masai.bean;

import java.util.Scanner;

public class BDOLogin {
	
	private String Userid;
	private String Userpassword;
	private String Email;
	private String BDOid;
	private String BDOpassword;
	
	
    public BDOLogin() {
    	Scanner scn=new Scanner(System.in);
		System.out.println("Enter User ID");
		String id=scn.next();
		System.out.println("Enter User Password");
		String pass=scn.next();
		
		if(id.equals("Pranshu") && pass.equals("1234")) {
			System.out.println("Login Successful");
		}
		else {
			System.out.println("Invalid User ID and Password");
		}
	}
	
}

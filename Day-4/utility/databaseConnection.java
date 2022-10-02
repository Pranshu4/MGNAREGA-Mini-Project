package com.masai.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {
	public static Connection getConnection() {
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String cs="jdbc:mysql://localhost:3306/MGNREGAdb";
		try {
			conn=DriverManager.getConnection(cs,"root","Pranshu@24");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}

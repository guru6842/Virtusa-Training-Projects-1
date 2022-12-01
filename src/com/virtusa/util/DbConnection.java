package com.virtusa.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	  
	public static Connection getConnection() {
		try {
			Connection con=null;
			String cs="jdbc:mysql://localhost:3306/ApartmentMaintenanceSystem";
			con=DriverManager.getConnection(cs,"root","iasuruG@1433");
			return con;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	private  DbConnection() {
		
	}

}

package com.virtusa.authorization;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.virtusa.util.DbConnection;

public class Authorization implements IAuthorization{
	private static Logger log=LogManager.getLogger(Authorization.class);
	public boolean adminAuthorization(String userName,String password) 
	{
		String query ="select * from adminDb where user_name='"+userName+"'and password='"+password+"'";
		try(Connection con=DbConnection.getConnection();Statement stmt= con.createStatement();) {
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next()) {
				log.info(rs.getString(1));
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	public int userAuthorization(String userName,String password) 
	{	
		String query ="select flat_no from userDb where user_name='"+userName+"'and password='"+password+"'";
		try(Connection con=DbConnection.getConnection();Statement stmt = con.createStatement();) {
			
			ResultSet rs=stmt.executeQuery(query);
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
		
	}

}

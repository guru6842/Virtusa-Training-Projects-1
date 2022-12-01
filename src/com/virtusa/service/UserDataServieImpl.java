package com.virtusa.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.virtusa.util.DbConnection;

public class UserDataServieImpl implements UserDataServie{
	private static Logger log=LogManager.getLogger(UserDataServieImpl.class);
	public void profile(int flatNo) {

		String query="select * from tenants where flat_no="+flatNo+" ";
		try(Connection con=DbConnection.getConnection();Statement stmt=con.createStatement();)
		{
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next())
			{
				String s1="Name         : "+rs.getString(1)+" "+rs.getString(2);
				String s2="flat no      : "+rs.getInt(6);
				String s3="Aadhar no    : "+rs.getLong(5);
				String s4="Mobile no    : "+rs.getLong(3);
				String s5="Email  ID    : "+rs.getString(4);
				String s6="Date of joining:"+rs.getString(7);
				log.info(s1);
				log.info(s2);
				log.info(s3);
				log.info(s4);
				log.info(s5);
				log.info(s6);
				}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

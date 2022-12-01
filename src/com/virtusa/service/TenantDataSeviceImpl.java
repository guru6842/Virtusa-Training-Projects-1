package com.virtusa.service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Statement;

import com.virtusa.dto.TenantDetail;
import com.virtusa.util.DbConnection;

public class TenantDataSeviceImpl implements TenantDataService {
	private static Logger log=LogManager.getLogger(TenantDataSeviceImpl.class);
	public TenantDetail tenantInput()
	{
		String firstName;
		String lastName;
		String phoneNo;
		String email;
		String aadharNo;
		Scanner sc=new Scanner(System.in);
		while(true) {
			log.info("Enter the first name: ");
			firstName=sc.next();
			if(firstName.matches("[a-zA-Z]*") && firstName.length()>3)
				break;
			log.info("Incorrect first name format : ");
		}
		while(true) {
			log.info("Enter the last name: ");
			lastName=sc.next();
			if(lastName.matches("[a-zA-Z]*"))
				break;
			log.info("Incorrect last name format : ");
		}
		while(true) {
			log.info("Enter the phone number: ");
			phoneNo=sc.next();
			if(phoneNo.matches("[6-9][0-9]{9}"))
				break;
			log.info("Incorrect mobile number format : ");
		}
		while(true) {
			log.info("Enter the email: ");
			email=sc.next();
			if(email.matches("^[A-Za-z0-9+_.-]+@(.+)$"))
				break;
			log.info("Incorrect mail format : ");
		}
		while(true) {
			log.info("Enter the aadhar no: ");
			aadharNo=sc.next();
			if(aadharNo.matches("[1-9][0-9]{11}"))
				break;
			log.info("Incorrect aadhar number format : ");
		}
		log.info("Enter the flast no: ");
		int flatNo=sc.nextInt();
		TenantDetail obj=null;
		obj=new TenantDetail(firstName,lastName,phoneNo,email,aadharNo,flatNo);
		
		return obj;
	}

	public void listOfTenants() 
	{
		try(Connection con=DbConnection.getConnection();Statement stmt=con.createStatement();)
		{
			String query="select * from tenants";
			ResultSet rs=stmt.executeQuery(query);
			int count=0;
			while(rs.next())
			{
				if(count==0) {
					log.info("       ***List of Tenants***          ");
					log.info("--------------------------------------------------------------");
					log.info("| S.No  | Flat No |    Name   |    Mobile No   |    Email ");
					log.info("---------------------------------------------------------------");
				}
				count++;
				String out="   "+count+"    :    "+rs.getInt(6)+"\t"+rs.getString(1)+" "+rs.getString(2)+"\t      "+rs.getString(3)+"     "+rs.getString(4);
				log.info(out);
			}
			if(count==0)
			{
				log.info("--------------------------------------");
				log.info("No Tenanats are present");
				log.info("--------------------------------------");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean addNewTenant(TenantDetail obj) 
	{
		
		String query="insert into tenants value(?,?,?,?,?,?,sysdate())";
		String query1="insert into userdb value(?,?,?)";
		try(Connection con=DbConnection.getConnection();PreparedStatement pt=con.prepareStatement(query);PreparedStatement pt1=con.prepareStatement(query1);) {
			if(obj.getPhoneNo().matches("[0-9]{10}") && obj.getAadharNo().matches("[0-9]{12}"))
			{
				
				String password=obj.getFirstName()+"@123";

				pt1.setInt(1,obj.getFlatNo());
				pt1.setString(2,obj.getPhoneNo());
				pt1.setString(3,password);
				int affectedRows1 = pt1.executeUpdate();

				pt.setString(1, obj.getFirstName());
				pt.setString(2, obj.getLastName());
				pt.setString(3, obj.getPhoneNo());
				pt.setString(4, obj.getEmail());
				pt.setString(5, obj.getAadharNo());
				pt.setInt(6, obj.getFlatNo());
				int affectedRows = pt.executeUpdate();
				
				
			
				if(affectedRows>0 && affectedRows1>0) {
					return true;
				}

			}
		}
		catch (Exception e) {
			log.info("The tenant with the same mobile number already exists");
		}
		return false;

	}
	public boolean searchTenant(int flatNo) {
		try(Connection con=DbConnection.getConnection();Statement stmt=con.createStatement();)
		{
			String query="select * from tenants where flat_no="+flatNo+" ";
			ResultSet rs=stmt.executeQuery(query);
			
			while(rs.next())
			{
				String s1="Name         : "+rs.getString(1)+" "+rs.getString(2);
				String s2="flat no      : "+rs.getInt(6);
				String s3="Aadhar no    : "+rs.getLong(5);
				String s4="Mobile no    : "+rs.getLong(3);
				String s5="Email  ID    : "+rs.getString(4);
				String s6="Date of joining:"+rs.getString(7);
				log.info("--------------------------------------");
				log.info(s1);
				log.info(s2);
				log.info(s3);
				log.info(s4);
				log.info(s5);
				log.info(s6);
				log.info("--------------------------------------");
				
				return true;
			}
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean deleteTenant(int flatNo)
	{
		
		String query="DELETE FROM tenants WHERE flat_no="+flatNo+" ";
		String query1="DELETE FROM userdb WHERE flat_no="+flatNo+" ";
		try (Connection con=DbConnection.getConnection();PreparedStatement pstmt = con.prepareStatement(query);PreparedStatement pstmt1 = con.prepareStatement(query1);){
			
			int affectedRows =pstmt.executeUpdate();
			int affectedRows1 =pstmt1.executeUpdate();
			
			if(affectedRows>0 && affectedRows1>0)
			{
				return true;
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean editTenant(int flatNo) {
		boolean flag=deleteTenant(flatNo);
		if(flag)
		{
			TenantDetail dto=tenantInput();
			return addNewTenant(dto);
		}
		return false;
	}
	public boolean usernamepwd(int flatNo)
	{
		try(Connection con=DbConnection.getConnection();Statement stmt=con.createStatement();) {
			
			String query1="select * from userdb where flat_no="+flatNo+" ";
			ResultSet rs1=stmt.executeQuery(query1);
			while(rs1.next())
			{
				String s7="User Name    : "+rs1.getString(2);
				String s8="Password     : "+rs1.getString(3);
				log.info("--------------------------------------");
				log.info(s7);
				log.info(s8);
				log.info("--------------------------------------");
				return true;
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	

}
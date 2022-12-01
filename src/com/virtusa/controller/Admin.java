package com.virtusa.controller;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.virtusa.dto.TenantDetail;
import com.virtusa.exception.DataNotFoundException;
import com.virtusa.service.TenantDataSeviceImpl;

public class Admin {
	private static Logger log=LogManager.getLogger(Admin.class);
	public void adminService(){
		
		TenantDataSeviceImpl service=new TenantDataSeviceImpl();
		int choice=0;
		Scanner sc=new Scanner(System.in);
		do {
			log.info(".....Menu.....");
			log.info("1.List of tenants");
			log.info("2.Add new tenant");
			log.info("3.Search tenant");
			log.info("4.delete tenant data");
			log.info("5.Edit tenant data");
			log.info("6.User name & password ");
			log.info("7.Exit....");
			log.info("Enter your choice :");
			choice=sc.nextInt();
			if(choice==1) 
			{
				service.listOfTenants();
			}
			else if(choice==2) 
			{
				TenantDetail dto=service.tenantInput();
				boolean flag=service.addNewTenant(dto);
				if(flag)
				{
					log.info("Successfully added.........");
				}
				else {
					log.info("Addition failed...!!!!");
				}
			}
			else if(choice==3) 
			{
				log.info("Enter the flat number :");
				int flatNo=sc.nextInt();
				boolean flag= service.searchTenant(flatNo);
				if(!flag)
				{
					try {
						throw new DataNotFoundException("data not found ");
					}
					catch (DataNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			else if(choice==4) 
			{
				log.info("Enter the flat number :");
				int  flatNo=sc.nextInt();
				boolean flag=service.deleteTenant(flatNo);
				if(flag)
				{
					log.info("Successfully Deleted.........");
				}
				else {
					try {
						throw new DataNotFoundException("Deleation fail");
					}
					catch (DataNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			else if(choice==5) 
			{
				log.info("Enter the flat number :");
				int  flatNo=sc.nextInt();
				boolean flag=service.editTenant(flatNo);
				if(flag)
				{
					log.info("Successfully Updated.........");
				}
				else {
					try {
						throw new DataNotFoundException("Updation fail");
					}
					catch (DataNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			else if(choice==6) 
			{
				log.info("Enter the flat number :");
				int flatNo=sc.nextInt();
				boolean flag= service.usernamepwd(flatNo);
				if(!flag)
				{
					try {
						throw new DataNotFoundException("data not found ");
					}
					catch (DataNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}while(choice<7);
		log.info("Successfully done.....!!");


	}

}

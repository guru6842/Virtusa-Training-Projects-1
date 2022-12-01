package com.virtusa.controller;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.virtusa.exception.DataNotFoundException;
import com.virtusa.service.TenantDataSeviceImpl;
import com.virtusa.service.UserDataServieImpl;

public class User {
	private static Logger log=LogManager.getLogger(User.class);
	TenantDataSeviceImpl service=new TenantDataSeviceImpl();
	public void userService(int flatNo) {
		int choice=0;
		UserDataServieImpl userObj=new UserDataServieImpl();
		Scanner sc=new Scanner(System.in);
		do {
			log.info(".....Menu.....");
			log.info("1.Profil ");
			log.info("2.Search ");
			log.info("3.List of tenants");
			log.info("4.Exit....");
			log.info("Enter your choice :");
			choice=sc.nextInt();
			if( choice==1) 
			{
				userObj.profile(flatNo);
			}
			else if(choice==2) {
				log.info("Enter the flat number :");
				int searchFlatNo=sc.nextInt();
				boolean flag= service.searchTenant(searchFlatNo);
				if(!flag)
				{
					try {
						throw new DataNotFoundException("flat number not found ");
					}
					catch (DataNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			else if(choice==3) 
			{
				service.listOfTenants();
			}
			else {
				log.info("Enter correct option");
			}

		}while(choice<4);
		log.info("Successfully done.....!!");
		sc.close();

	}

}

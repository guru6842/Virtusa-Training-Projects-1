package com.virtusa.main;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.virtusa.authorization.Authorization;
import com.virtusa.controller.Admin;
import com.virtusa.controller.User;

public class Main {
	private static Logger log=LogManager.getLogger(Main.class);
	public static void main(String[] args)  {
		Scanner sc=new Scanner(System.in);
		Authorization authz= new Authorization();
		while(true) {
			log.info("Enter your choice :");
			log.info("\t 1. Admin");
			log.info("\t 2. User");
			int temp=sc.nextInt();
			
			if(temp==1)
			{
				log.info("Enter User name: ");
				String userName=sc.next();
				log.info("Enter password");
				String password=sc.next();
				boolean flag= authz.adminAuthorization(userName, password);
				if(flag )
				{
					Admin obj=new Admin();
					obj.adminService();
					break;
				}
				else {
					log.info("user name or password wrong...");
				}
			}
			else if(temp==2)
			{
				log.info("Enter User name: ");
				String userName=sc.next();
				log.info("Enter password");
				String password=sc.next();
				int flag=authz.userAuthorization(userName,password);
				if(flag>0)
				{
					User obj1=new User();
					obj1.userService(flag);
					break;
				}
				else {
					log.info("user name or password wrong...");
				}
			}
			else {
				log.info("Invalid Choice :");
			}
		}
		sc.close();
	}

}

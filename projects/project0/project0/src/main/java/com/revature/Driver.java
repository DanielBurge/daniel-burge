package com.revature;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.exceptions.LoginException;

import com.revature.models.User;

import com.revature.services.Authservice;

import com.revature.services.UserService;

public class Driver {
	
	static Scanner scan;
	static Authservice as;
	static UserService us;
	
	private static Logger log = LogManager.getLogger(Driver.class);
	
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		as = new Authservice();
		us = new UserService();
		
		
		String username = null;
		String password = null;
		
		System.out.println("Please enter username:");
		username = scan.nextLine();
		System.out.println("Please enter password:");
		password = scan.nextLine();
		
		try {
			log.info(as.login(username, password));
		} catch (LoginException e) {
			System.out.println("Wrong username/password");
			log.error("Login exception was thrown: " + e.fillInStackTrace());
//			e.printStackTrace(); 
		}

		
		List<User> users = us.getUsers();
		for(User u : users) {
			System.out.println(u);
		}	
		// "1; drop table users"
		System.out.println("Create an username:");
		String uname = scan.nextLine();
		System.out.println("Create a password:");
		String pass = scan.nextLine();
		User userTBC = new User();
		userTBC.setUsername(uname);
		userTBC.setPassword(pass);
		log.info(us.createUser(userTBC));
		
		
		scan.close();
	

	}

}

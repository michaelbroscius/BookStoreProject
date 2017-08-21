package com.mindteck.broscius.varialibrorum.web.application;

import javax.servlet.http.HttpSession;

import com.mindteck.broscius.varialibrorum.data.entity.User;

public class LoginChecker {
	public static boolean isUserAuthenticated(HttpSession session) {
		User user = (User) session.getAttribute("user");
		System.out.println("\n\n\n*********************************************");
		System.out.println("isUserAuthenticated: user = " + user);
		System.out.println("*********************************************\n\n\n");
		return user != null;
	}

}

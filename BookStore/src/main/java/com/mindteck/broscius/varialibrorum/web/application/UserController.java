package com.mindteck.broscius.varialibrorum.web.application;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mindteck.broscius.varialibrorum.business.service.UserService;
import com.mindteck.broscius.varialibrorum.data.entity.User;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/user")
	public String showUserForm(User user) {
		return "user";
	}

	@PostMapping("/user")
	public String saveUser(@Valid User user, BindingResult BindingResult, Model model) {

		if (!BindingResult.hasErrors()) {
			System.out.println(user.getId() + " / " + user.getEmail() + " / " + user.getPassword());
			userService.add(user);
		}

		return "user";
	}
}

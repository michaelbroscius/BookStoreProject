package com.mindteck.broscius.varialibrorum.web.application;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mindteck.broscius.varialibrorum.business.service.UserService;
import com.mindteck.broscius.varialibrorum.data.entity.User;
import com.mindteck.broscius.varialibrorum.data.form.UserRegistrationForm;

@Controller
public class UserRegistrationController {

	@Autowired
	UserService userService;

	@GetMapping("/userRegistration")
	public String showUserForm(UserRegistrationForm userForm) {
		return "userRegistration";
	}

	@PostMapping("/userRegistration")
	public String saveUser(@Valid UserRegistrationForm userRegistrationForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "userRegistration";
		}
		System.out.println("UserController.saveUser bindingResult" + bindingResult);
		
		User user = userService.add(userRegistrationForm);
		System.out.println(user.getId() + " / " + user.getEmail() + " / " + user.getPassword());

		return "redirect:/login";
	}
}

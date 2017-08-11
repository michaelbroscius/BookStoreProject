package com.mindteck.broscius.varialibrorum.web.application;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mindteck.broscius.varialibrorum.business.service.UserService;
import com.mindteck.broscius.varialibrorum.data.entity.User;
import com.mindteck.broscius.varialibrorum.web.form.UserManagementForm;

@Controller
public class UserManagementController {
	@Autowired
	UserService userService;

	@GetMapping("/userManagement")
	public String showUserForm(UserManagementForm userManagementForm) {
		return "userManagement";
	}

	@PostMapping("/userManagement")
	public String saveUser(@Valid UserManagementForm userManagementForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "userManagement";
		}

		User user = userService.add(userManagementForm);
		System.out.println(user.getId() + " / " + user.getEmail() + " / " + user.getPassword());

		return "userManagement";
	}
}

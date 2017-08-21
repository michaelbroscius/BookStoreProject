package com.mindteck.broscius.varialibrorum.web.application;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mindteck.broscius.varialibrorum.business.service.UserService;
import com.mindteck.broscius.varialibrorum.data.entity.User;
import com.mindteck.broscius.varialibrorum.data.form.UserManagementForm;

@Controller
public class UserManagementController {
	@Autowired
	UserService userService;

	@GetMapping("/userManagement")
	public String showUserForm(UserManagementForm userManagementForm, Model model) {
		addUsersToModel(model);
		return "userManagement";
	}

	//TODO implement security
	@PostMapping("/userManagement")
	public String saveUser(@Valid UserManagementForm userManagementForm, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "userManagement";
		}

		User user = userService.add(userManagementForm);
		System.out.println(user.getId() + " / " + user.getEmail() + " / " + user.getPassword());
		
		addUsersToModel(model);

		return "userManagement";
	}

	/**
	 * @param model
	 */
	public Model addUsersToModel(Model model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return model;
	}
}

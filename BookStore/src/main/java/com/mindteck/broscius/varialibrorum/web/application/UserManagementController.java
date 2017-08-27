package com.mindteck.broscius.varialibrorum.web.application;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private final Logger logger = LoggerFactory.getLogger(UserManagementController.class);

	@Autowired
	UserService userService;

	@GetMapping("/userManagement")
	public String showUserForm(UserManagementForm userManagementForm, Model model) {
		logger.debug("Entered showUserForm(userManagermentForm, model for @GetMapping(\"/userManagement\")");
		addUsersToModel(model);
		logger.debug("showUserForm() returning \"userManagement\".");
		return "userManagement";
	}

	// TODO implement security
	@PostMapping("/userManagement")
	public String saveUser(@Valid UserManagementForm userManagementForm, BindingResult bindingResult, Model model) {
		logger.debug(
				"Entered saveUser(userManagermentForm, bindingResult, model for @PostMapping(\"/userManagement\")");
		logger.debug("userManagementForm: {}.", userManagementForm);

		if (bindingResult.hasErrors()) {
			logger.debug("bindingResult has errors. Returning to userManagement.");
			return "userManagement";
		}

		User user = userService.add(userManagementForm);
		logger.debug("User added. User: {}. ", user);

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

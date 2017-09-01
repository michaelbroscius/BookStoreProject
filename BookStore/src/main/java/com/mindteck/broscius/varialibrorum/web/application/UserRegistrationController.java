package com.mindteck.broscius.varialibrorum.web.application;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private final Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);

	@Autowired
	UserService userService;

	@GetMapping("/userRegistration")
	public String showUserForm(UserRegistrationForm userRegistrationForm) {
		logger.debug("Entered showUserForm(userRegistrationForm) for @GetMapping(\"/userRegistration\")");
		logger.debug("userRegistrationForm: {}. Returning userRegistration.", userRegistrationForm);
		return "userRegistration";
	}

	@PostMapping("/userRegistration")
	public String saveUser(@Valid UserRegistrationForm userRegistrationForm, BindingResult bindingResult, HttpSession session) {
		logger.debug("Entered saveUser(@Valid UserRegistrationForm, BindingResult) for @PostMapping(\"/userRegistration\")");
		logger.debug("userRegistrationForm: {}.", userRegistrationForm);

		if (bindingResult.hasErrors()) {
			logger.debug("bindingResult has errors, returning to page.");
			return "userRegistration";
		}

		User user = userService.add(userRegistrationForm);
		logger.info("User registered: {}", user);

		session.setAttribute("registrationmessage", "Your account has been created. Please log in.");
		logger.debug("saveUser() returning \"redirect:/login\"");
		return "redirect:/login";
	}
}

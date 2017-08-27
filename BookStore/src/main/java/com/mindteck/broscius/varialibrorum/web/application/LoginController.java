package com.mindteck.broscius.varialibrorum.web.application;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mindteck.broscius.varialibrorum.business.service.UserService;
import com.mindteck.broscius.varialibrorum.business.service.impl.AuthenticationException;
import com.mindteck.broscius.varialibrorum.data.entity.User;
import com.mindteck.broscius.varialibrorum.data.form.LoginForm;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	// User parameter needed for bean-backed form
	public String showLoginPage(LoginForm loginForm) {
		logger.debug("Entered showLoginPage(LoginForm loginForm) where loginForm = {}", loginForm);
		logger.debug("showLoginPage() returning  \"login\"");
		return "login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String validateUser(@Valid LoginForm loginForm, BindingResult bindingResult, Model model,
			HttpSession session) {

		logger.debug("Entered validateUser(@Valid LoginForm loginForm, BindingResult bindingResult, Model model,\r\n"
				+ "			HttpSession session) where loginForm = {}", loginForm);

		User user = null;

		if (!bindingResult.hasErrors()) {
			logger.debug("no errors in bindingResult.");
			try {
				if (null != (user = userService.validateUser(loginForm.getEmail(), loginForm.getPassword()))) {
					session.setAttribute("user", user);
					logger.info("User {} logged in.", user);
					logger.debug(" returning \"forward:/index\"");
					return "forward:/index";
				}
			} catch (AuthenticationException e) {
				logger.info("Caught error " + e + ": E-mail or password is incorrect. E-mail: {}.",
						loginForm.getEmail());
				model.addAttribute("errorMessage", "E-mail or password is incorrect.");
			}
		}
		logger.debug("bindingResult: " + bindingResult);

		logger.debug("Returning \"login\"");
		return "login";
	}

}

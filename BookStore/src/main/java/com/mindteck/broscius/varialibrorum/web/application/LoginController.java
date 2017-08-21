package com.mindteck.broscius.varialibrorum.web.application;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
class LoginController {

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	// User parameter needed for bean-backed form
	public String showLoginPage(LoginForm loginForm) {
		return "login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String validateUser(@Valid LoginForm loginForm, BindingResult bindingResult, Model model,
			HttpSession session) {

		User user = null;

		if (!bindingResult.hasErrors()) {
			System.out.println("LoginController.validateUser: no errors in bindingResult.");
			try {
				if (null != (user = userService.validateUser(loginForm.getEmail(), loginForm.getPassword()))) {
					session.setAttribute("user", user);
//					session.setAttribute("userEmail", loginForm.getEmail());
//					session.setAttribute("userRole", user.getRole());
					System.out.println("LoginController.validateUser returning index");
					return "forward:/index";
				}
			} catch (AuthenticationException e) {
				System.out.println("LoginController.validateUser caught error");
				model.addAttribute("errorMessage", "E-mail or password is incorrect.");
			}
		}
		System.out.println("LoginController.validateUser: " + bindingResult);

		System.out.println("LoginController.validateUser returning login");
		return "login";
	}

}

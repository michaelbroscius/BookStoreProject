package com.mindteck.broscius.varialibrorum.web.application;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mindteck.broscius.varialibrorum.business.service.OrderService;
import com.mindteck.broscius.varialibrorum.data.entity.Order;
import com.mindteck.broscius.varialibrorum.data.entity.User;

@Controller
public class OrderHistoryController {

	@Autowired
	OrderService orderService;

	@GetMapping(value = { "/orderhistory" })
	public String showCheckout(Order order, Model model, HttpSession session) {
		if (!ControllerUtilities.isUserAuthenticated(session)) {
			session.invalidate();
			return "redirect:/login";
		}

		User user = (User) session.getAttribute("user");
		System.out.println("\n*****     sent to order history controller by GET   ****");
		System.out.println("*****     user: " + user + "     ************************");

		model.addAttribute("orders", orderService.getOrdersForUser(user));

		return "orderhistory";
	}

}

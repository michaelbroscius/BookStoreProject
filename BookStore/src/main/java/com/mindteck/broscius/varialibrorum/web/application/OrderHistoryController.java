package com.mindteck.broscius.varialibrorum.web.application;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mindteck.broscius.varialibrorum.business.service.OrderService;
import com.mindteck.broscius.varialibrorum.data.entity.Order;
import com.mindteck.broscius.varialibrorum.data.entity.User;

@Controller
public class OrderHistoryController {

	private final Logger logger = LoggerFactory.getLogger(OrderHistoryController.class);

	@Autowired
	OrderService orderService;

	@GetMapping(value = { "/orderhistory" })
	public String showCheckout(Order order, Model model, HttpSession session) {
		logger.debug(
				"Entered showCheckout(Order order, Model model, HttpSession session) for @GetMapping(value = { \"/orderhistory\" })");
		logger.debug("order: {}.", order);

		if (!ControllerUtilities.isUserAuthenticated(session)) {
			session.invalidate();
			return "redirect:/login";
		}

		User user = (User) session.getAttribute("user");
		logger.debug("logged in user: {}.", user);

		List<Order> orders = orderService.getOrdersForUser(user);
		logger.debug("adding user's orders to model. User: {}, orders: {}.", user, orders);
		model.addAttribute("orders", orders);

		return "orderhistory";
	}

}

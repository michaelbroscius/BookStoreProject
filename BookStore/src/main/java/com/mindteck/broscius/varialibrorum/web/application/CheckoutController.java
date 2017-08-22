package com.mindteck.broscius.varialibrorum.web.application;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mindteck.broscius.varialibrorum.business.service.CheckoutService;
import com.mindteck.broscius.varialibrorum.business.service.ShoppingCartService;
import com.mindteck.broscius.varialibrorum.data.entity.Order;
import com.mindteck.broscius.varialibrorum.data.entity.User;

@Controller
public class CheckoutController {

	@Autowired
	CheckoutService checkoutService;
	@Autowired
	ShoppingCartService shoppingCartService;

	@GetMapping("/checkoutpage")
	public String showCheckout(Model model, HttpSession session) {
		if (!ControllerUtilities.isUserAuthenticated(session)) {
			session.invalidate();
			return "redirect:/login";
		}

		User user = (User) session.getAttribute("user");
		System.out.println("\n\n**************************************************************");
		System.out.println("*****     sent to checkout controller by GET   ****");
		System.out.println("*****     user: " + user + "     ************************");

		ControllerUtilities.addUserCartToModel(user, model, shoppingCartService);
		addOrderToModel(user, model);
		System.out.println("**************************************************\n");

		return "checkout";
	}

	@GetMapping("/orderconfirmation")
	public String placeOrder(Model model, HttpSession session) {
		if (!ControllerUtilities.isUserAuthenticated(session)) {
			session.invalidate();
			return "redirect:/login";
		}

		User user = (User) session.getAttribute("user");
		System.out.println("\n\n**************************************************************");
		System.out.println("*****     sent to checkout controller by GET   ****");
		System.out.println("*****     user: " + user + "     ************************");

		// addUserCartToModel(user, model);
		System.out.println("**************************************************");

		return "checkout";
	}

	public Model addOrderToModel(User user, Model model) {
		Order order = checkoutService.getOrderForUser(user);
		model.addAttribute("order", order);

		System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("ShoppingCartController.addOrderToModel order: " + order);
		System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");

		return model;
	}

}

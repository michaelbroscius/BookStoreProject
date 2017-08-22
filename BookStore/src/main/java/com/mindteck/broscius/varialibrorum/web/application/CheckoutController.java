package com.mindteck.broscius.varialibrorum.web.application;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	public String showCheckout(Order order, Model model, HttpSession session) {
		if (!ControllerUtilities.isUserAuthenticated(session)) {
			session.invalidate();
			return "redirect:/login";
		}

		User user = (User) session.getAttribute("user");
		System.out.println("\n*****     sent to checkout controller by GET   ****");
		System.out.println("*****     user: " + user + "     ************************");

		ControllerUtilities.addUserCartToModel(user, model, shoppingCartService);
		model.addAttribute("order", new Order());

		return "checkout";
	}

	@PostMapping("/orderconfirmation")
	// TODO check if order parameter is needed
	public String placeOrder(Order order, BindingResult bindingResult, Model model, HttpSession session) {
		if (!ControllerUtilities.isUserAuthenticated(session)) {
			session.invalidate();
			return "redirect:/login";
		}

		User user = (User) session.getAttribute("user");
		System.out.println("\n*****     sent to placeorder controller by GET   ****");
		System.out.println("*****     user: " + user + "     ************************");
		Order processedOrder = checkoutService.checkout(user);
		System.out.println("CheckoutController.placeOrder: order: " + processedOrder);

		return "ordersummary";
	}

//	@GetMapping("/ordersummary")
//	// TODO check if order parameter is needed
//	public String summarizeOrder(Model model, HttpSession session) {
//		if (!ControllerUtilities.isUserAuthenticated(session)) {
//			session.invalidate();
//			return "redirect:/login";
//		}
//
//		User user = (User) session.getAttribute("user");
//		System.out.println("\n*****     sent to placeorder controller by GET   ****");
//		System.out.println("*****     user: " + user + "     ************************");
//		Order order = checkoutService.getOrderForUser(user);
//		System.out.println("CheckoutController.placeOrder: order: " + order);
//
//		return "ordersummary";
//	}

	public Model addOrderToModel(User user, Model model) {
		Order order = checkoutService.getOrderForUser(user);
		model.addAttribute("order", order);

		System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("ShoppingCartController.addOrderToModel order: " + order);
		System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");

		return model;
	}

}

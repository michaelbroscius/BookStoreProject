package com.mindteck.broscius.varialibrorum.web.application;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mindteck.broscius.varialibrorum.business.service.CheckoutService;
import com.mindteck.broscius.varialibrorum.business.service.ShoppingCartService;
import com.mindteck.broscius.varialibrorum.data.entity.Order;
import com.mindteck.broscius.varialibrorum.data.entity.ShoppingCart;
import com.mindteck.broscius.varialibrorum.data.entity.User;

@Controller
@SessionAttributes("shoppingcart" )
public class CheckoutController {
	private final Logger logger = LoggerFactory.getLogger(CheckoutController.class);

	@Autowired
	CheckoutService checkoutService;
	@Autowired
	ShoppingCartService shoppingCartService;

	@GetMapping(value = "/checkoutpage")
	public String showCheckout(Order order, @ModelAttribute("shoppingcart") ShoppingCart shoppingCart, Model model, HttpSession session) {
		logger.debug(
				"Entered showCheckout(Order order, Model model, HttpSession session) for @GetMapping(value = { \"/checkoutpage\", \"/checkout.html\" })");
		logger.debug("Order: {}.", order);
		logger.debug("ShoppingCart: {}.", shoppingCart);

		if (!ControllerUtilities.isUserAuthenticated(session)) {
			session.invalidate();
			logger.info("An attempt was made to show checkout page while not logged in.");
			logger.debug("showCheckout() returning \"redirect:/login\".");
			return "redirect:/login";
		}

		User user = (User) session.getAttribute("user");
		logger.debug("Logged in user: {}.", user);

		logger.debug("Adding new order as model attribute.");
		model.addAttribute("order", new Order());

		logger.debug("showCheckout() returning \"checkout\".");
		return "checkout";
	}

	@PostMapping("/orderconfirmation")
	public String placeOrder(Order order, BindingResult bindingResult, Model model, HttpSession session) {
		logger.debug(
				"Entered placeOrder(Order order, BindingResult bindingResult, Model model, HttpSession session) for @PostMapping(\"/orderconfirmation\").");
		logger.debug("Order: {}.", order);

		if (!ControllerUtilities.isUserAuthenticated(session)) {
			session.invalidate();
			logger.info("An attempt was made to show checkout page while not logged in.");
			logger.debug("placeOrder() returning \"redirect:/login\".");
			return "redirect:/login";
		}

		User user = (User) session.getAttribute("user");
		logger.debug("Logged in user: {}.", user);

		Order processedOrder = checkoutService.checkout(order, user);
		logger.debug("************************\n\nCart after checking out: {}\n\n******************", getShoppingCart(session));
		logger.info("User {}\n placed order {}.", user, order);
		logger.debug("processed order: {}.", processedOrder);

		return "ordersummary";
	}

	public Model addOrderToModel(User user, Model model) {
		Order order = checkoutService.getOrderForUser(user);
		model.addAttribute("order", order);

		logger.debug("addOrderToModel(): user: {} order: {}", user, order);

		return model;
	}
	
	@ModelAttribute("shoppingcart")
	public ShoppingCart getShoppingCart(HttpSession session) {
		logger.debug("Entered getShoppingCart()");
		User user = (User) session.getAttribute("user");
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCartForUser(user);

		logger.debug("getShoppingCart(): user is {}. Returning shoppingCart: {}.", user, shoppingCart);
		return shoppingCart;
	}

}

package com.mindteck.broscius.varialibrorum.web.application;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mindteck.broscius.varialibrorum.business.service.BookService;
import com.mindteck.broscius.varialibrorum.business.service.ShoppingCartService;
import com.mindteck.broscius.varialibrorum.data.entity.ShoppingCart;
import com.mindteck.broscius.varialibrorum.data.entity.User;

@Controller
public class ShoppingCartController {
	private final Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

	@Autowired
	BookService bookService;

	@Autowired
	ShoppingCartService shoppingCartService;

	@GetMapping("/addtoshoppingcart")
	public String addToShoppingCart(@RequestParam(value = "id", required = false) Long id, Model model,
			HttpSession session) {
		logger.debug(
				"Entered ShoppingCartController.addToShoppingCart(@RequestParam(value = \"id\", required = false) Long id, Model model,\r\n"
						+ "			HttpSession session) for @GetMapping(\"/addtoshoppingcart\")   ****");
		logger.debug("id: {}.", id);
		if (!ControllerUtilities.isUserAuthenticated(session)) {
			logger.info("User not logged in. Redirecting to login page.");
			session.invalidate();
			return "redirect:/login";
		}

		User user = (User) session.getAttribute("user");

		logger.debug("addToShoppingCart() adding item to cart.");
		ShoppingCart shoppingCart = shoppingCartService.addItemToUserCart(user, id, 1);
		logger.debug("addToShoppingCart() adding user's cart to model. user:{}, cart:{}.", user, shoppingCart);
		ControllerUtilities.addUserCartToModel(user, model, shoppingCartService);
		logger.debug("cart: {}.", shoppingCart);

		logger.debug("addToShoppingCart() returning \"shoppingcart\"");
		return "shoppingcart";
	}

	// TODO determine if two methods are really necessary here
	@GetMapping("/showshoppingcart")
	public String addToShoppingCart(Model model, HttpSession session) {
		logger.debug("addToShoppingCart(Model model, HttpSession session @GetMapping(\"/showshoppingcart\"))");
		if (!ControllerUtilities.isUserAuthenticated(session)) {
			logger.info("User not logged in. Redirecting to login page.");
			session.invalidate();
			return "redirect:/login";
		}

		User user = (User) session.getAttribute("user");

		ShoppingCart shoppingCart = shoppingCartService.getShoppingCartForUser(user);
		ControllerUtilities.addUserCartToModel(user, model, shoppingCartService);
		logger.debug("cart: {}. ", shoppingCart);
		logger.debug("addToShoppingCart(Model, HttpSession) returning \"shoppingcart\".");
		return "shoppingcart";
	}

}

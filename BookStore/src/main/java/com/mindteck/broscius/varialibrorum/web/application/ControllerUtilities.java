package com.mindteck.broscius.varialibrorum.web.application;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mindteck.broscius.varialibrorum.data.entity.User;

public class ControllerUtilities {
	private static final Logger logger = LoggerFactory.getLogger(ControllerUtilities.class);

	public static boolean isUserAuthenticated(HttpSession session) {
		User user = (User) session.getAttribute("user");
		logger.debug("isUserAuthenticated(): user: {}.", user);
		return user != null;
	}

	// public static Model addUserCartToModel(User user, Model model,
	// ShoppingCartService shoppingCartService) {
	// logger.debug("Entered addUserCartToModel() user: {}. ", user);
	// ShoppingCart shoppingCart = shoppingCartService.getShoppingCartForUser(user);
	// model.addAttribute("shoppingcart", shoppingCart);
	//
	// logger.debug("addUserCartToModel() shoppingCart: {}.", shoppingCart);
	//
	// return model;
	// }

}

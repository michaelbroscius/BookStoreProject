package com.mindteck.broscius.varialibrorum.web.application;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.mindteck.broscius.varialibrorum.business.service.ShoppingCartService;
import com.mindteck.broscius.varialibrorum.data.entity.ShoppingCart;
import com.mindteck.broscius.varialibrorum.data.entity.User;

public class ControllerUtilities {
	public static boolean isUserAuthenticated(HttpSession session) {
		User user = (User) session.getAttribute("user");
		System.out.println("\n\n\n*********************************************");
		System.out.println("isUserAuthenticated: user = " + user);
		System.out.println("*********************************************\n\n\n");
		return user != null;
	}

	public static Model addUserCartToModel(User user, Model model, ShoppingCartService shoppingCartService) {
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCartForUser(user);
		model.addAttribute("shoppingcart", shoppingCart);

		System.out.println(
				"\n\n ################## ShoppingCartController.addUserCartToModel cart: " + shoppingCart.getCart());

		return model;
	}

}

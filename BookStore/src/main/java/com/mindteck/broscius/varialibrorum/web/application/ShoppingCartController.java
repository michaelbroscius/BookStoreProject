package com.mindteck.broscius.varialibrorum.web.application;

import javax.servlet.http.HttpSession;

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
	@Autowired
	BookService bookService;
	@Autowired
	ShoppingCartService shoppingCartService;

	@GetMapping("/addtoshoppingcart")
	public String addToShoppingCart(@RequestParam(value = "id", required = false) Long id, Model model,
			HttpSession session) {
		System.out.println("\n*****     sent to ShoppingCartController.addToShoppingCart @GetMapping(\"/addtoshoppingcart\")   ****");
		if (!ControllerUtilities.isUserAuthenticated(session)) {
			System.out.println("User not logged in. Redirecting to login page.");
			session.invalidate();
			return "redirect:/login";
		}

		User user = (User) session.getAttribute("user");
		
		System.out.println("**     id sent: " + id + "   user: " + user +   "*******************");
		System.out.println("ShoppingCartController.addToShoppingCart adding item to cart.");
		ShoppingCart shoppingCart = shoppingCartService.addItemToUserCart(user, id, 1);
		System.out.println("ShoppingCartController.addToShoppingCart adding user's cart to model.");
		System.out.println("cart: " + shoppingCart);
		System.out.println("adding cart to model"); 
		ControllerUtilities.addUserCartToModel(user, model, shoppingCartService);
		System.out.println("*****     cart: " + shoppingCart + "     ************************\n");

		return "shoppingcart";
	}

	@GetMapping("/showshoppingcart")
	public String addToShoppingCart(Model model, HttpSession session) {
		System.out.println("\n*****     sent to ShoppingCartController.addToShoppingCart(Model model, HttpSession session @GetMapping(\"/showshoppingcart\")) *****");
		if (!ControllerUtilities.isUserAuthenticated(session)) {
			System.out.println("User not logged in. Redirecting to login page.");
			session.invalidate();
			return "redirect:/login";
		}

		User user = (User) session.getAttribute("user");

		ShoppingCart shoppingCart = shoppingCartService.getShoppingCartForUser(user);
		ControllerUtilities.addUserCartToModel(user, model, shoppingCartService);
		System.out.println("\n*****########     cart: " + shoppingCart + "     #########***************\n");

		return "shoppingcart";
	}

}

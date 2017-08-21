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
		if (!LoginChecker.isUserAuthenticated(session)) {
			session.invalidate();
			return "redirect:/login";
		}

		User user = (User) session.getAttribute("user");
		System.out.println("\n\n**************************************************************");
		System.out.println("*****     sent to shoppingcart controller by GET   ****");
		System.out.println("****     id sent: " + id + "    **********************");
		System.out.println("*****     user: " + user + "     ************************");

		ShoppingCart shoppingCart = shoppingCartService.addItemToUserCart(user, id, 1);
		addUserCartToModel(user, model);
		System.out.println("*****     cart: " + shoppingCart + "     ************************");

		return "shoppingcart";
	}

	@GetMapping("/showshoppingcart")
	public String addToShoppingCart(Model model, HttpSession session) {
		if (!LoginChecker.isUserAuthenticated(session)) {
			session.invalidate();
			return "redirect:/login";
		}

		User user = (User) session.getAttribute("user");

		ShoppingCart shoppingCart = shoppingCartService.getShoppingCartForUser(user);
		addUserCartToModel(user, model);
		System.out.println("*****     cart: " + shoppingCart + "     ************************");

		return "shoppingcart";
	}

	/**
	 * @param model
	 */
	public Model addUserCartToModel(User user, Model model) {
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCartForUser(user);
		model.addAttribute("cart", shoppingCart.getCart());
		
		System.out.println("ShoppingCartController.addUserCartToModel cart: " + shoppingCart.getCart());

		return model;
	}

}

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mindteck.broscius.varialibrorum.business.service.BookService;
import com.mindteck.broscius.varialibrorum.business.service.ShoppingCartService;
import com.mindteck.broscius.varialibrorum.data.entity.ShoppingCart;
import com.mindteck.broscius.varialibrorum.data.entity.User;

@Controller
@SessionAttributes("shoppingcart")
public class ShoppingCartController {
	private final Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

	@Autowired
	BookService bookService;

	@Autowired
	ShoppingCartService shoppingCartService;

	@GetMapping({"/addtoshoppingcart", "/checkout.html"}) 
	public String addToShoppingCart(@ModelAttribute("shoppingcart") ShoppingCart shoppingCart, BindingResult result,
			@RequestParam(value = "productid", required = false) Long productId, Model model, HttpSession session) {
		logger.debug(
				"Entered ShoppingCartController.addToShoppingCart(@RequestParam(value = \"id\", required = false) Long id, Model model,\r\n"
						+ "			HttpSession session) for @GetMapping(\"/addtoshoppingcart\").");
		logger.debug("productId: {}; shoppingCart:{}", productId, shoppingCart);
		if (!ControllerUtilities.isUserAuthenticated(session)) {
			logger.info("User not logged in. Redirecting to login page.");
			session.invalidate();
			return "redirect:/login";
		}

		User user = (User) session.getAttribute("user");

		if (productId != null) {
			logger.debug("addToShoppingCart() adding item to cart.");
			shoppingCart = shoppingCartService.addItemToUserCart(user, productId, 1);
		}
		logger.debug("addToShoppingCart() adding user's cart to model. user:{}, cart:{}.", user,
				shoppingCart);
		model.addAttribute("shoppingcart", shoppingCart);
		logger.debug("addToShoppingCart() returning \"shoppingcart\"");
		return "shoppingcart";
	}

	@PostMapping("/changequantityincart")
	public String changeQuantityInShoppingCart(@ModelAttribute("shoppingcart") ShoppingCart shoppingCart, BindingResult result,
			@RequestParam(value = "itemID") Long cartItemID, @RequestParam(value = "quantity") int quantity,
			Model model, HttpSession session) {
		logger.debug(
				"Entered changeQuantityInShoppingCart(@RequestParam(value = \"id\") Long id, @RequestParam(value = \"quantity\") int quantity, Model model,\r\n"
						+ "			HttpSession session) for @PostMapping(\"/changequantityincart\").");
		logger.debug("quantity:{}, cartID:{}, shoppingCart:{}.", quantity, cartItemID, shoppingCart.getUser(),
				shoppingCart);

		if (!ControllerUtilities.isUserAuthenticated(session)) {
			logger.info("User not logged in. Redirecting to login page.");
			session.invalidate();
			return "redirect:/login";
		}

		User user = (User) session.getAttribute("user");

		shoppingCart = shoppingCartService.addQuantityOfItemToUserCart(quantity, cartItemID, user);
		
		model.addAttribute("shoppingcart", shoppingCart);

		logger.debug("cart: {}.", shoppingCart);
		logger.debug("changeQuantityInShoppingCart() returning \"shoppingcart\"");
		return "shoppingcart";
	}

	// // TODO determine if two methods are really necessary here
	// @GetMapping("/showshoppingcart")
	// public String addToShoppingCart(Model model, HttpSession session) {
	// logger.debug("addToShoppingCart(Model model, HttpSession session
	// @GetMapping(\"/showshoppingcart\"))");
	// if (!ControllerUtilities.isUserAuthenticated(session)) {
	// logger.info("User not logged in. Redirecting to login page.");
	// session.invalidate();
	// return "redirect:/login";
	// }
	//
	// User user = (User) session.getAttribute("user");
	//
	// ShoppingCart shoppingCart = shoppingCartService.getShoppingCartForUser(user);
	// ControllerUtilities.addUserCartToModel(user, model, shoppingCartService);
	// logger.debug("cart: {}. ", shoppingCart);
	// logger.debug("addToShoppingCart(Model, HttpSession) returning
	// \"shoppingcart\".");
	// return "shoppingcart";
	// }

	@ModelAttribute("shoppingcart")
	public ShoppingCart getShoppingCart(HttpSession session) {
		logger.debug("Entered getShoppingCart()");
		User user = (User) session.getAttribute("user");
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCartForUser(user);

		logger.debug("getShoppingCart(): user is {}. Returning shoppingCart: {}.", user, shoppingCart);
		return shoppingCart;
	}

}

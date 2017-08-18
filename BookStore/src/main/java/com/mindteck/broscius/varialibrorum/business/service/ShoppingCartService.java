package com.mindteck.broscius.varialibrorum.business.service;

import com.mindteck.broscius.varialibrorum.data.entity.CartItem;
import com.mindteck.broscius.varialibrorum.data.entity.ShoppingCart;
import com.mindteck.broscius.varialibrorum.data.entity.User;

/**
 * @author Michael Broscius August, 2017
 *
 */

public interface ShoppingCartService {

	ShoppingCart getShoppingCartForUser(User user);

	ShoppingCart getShoppingCart(Long id);

	/**
	 * Ensure cartItem is in shoppingCart. If it was added, persists shoppingCart.
	 * 
	 * @param cartItem
	 * @param shoppingCart
	 * @return
	 */
	ShoppingCart addItemToCart(CartItem cartItem, ShoppingCart shoppingCart);

	ShoppingCart removeItemFromCart(CartItem cartItem, ShoppingCart shoppingCart);

	ShoppingCart clearCart(ShoppingCart shoppingCart);

}
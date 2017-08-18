package com.mindteck.broscius.varialibrorum.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindteck.broscius.varialibrorum.business.service.ShoppingCartService;
import com.mindteck.broscius.varialibrorum.data.entity.CartItem;
import com.mindteck.broscius.varialibrorum.data.entity.ShoppingCart;
import com.mindteck.broscius.varialibrorum.data.entity.User;
import com.mindteck.broscius.varialibrorum.data.repository.ShoppingCartRepository;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	ShoppingCartRepository shoppingCartRepository;

	@Transactional(readOnly = true)
	@Override
	public ShoppingCart getShoppingCartForUser(User user) {
		return shoppingCartRepository.findByUser(user);
	}

	@Transactional(readOnly = true)
	@Override
	public ShoppingCart getShoppingCart(Long id) {
		return shoppingCartRepository.findOne(id);
	}

	@Transactional
	@Override
	public ShoppingCart addItemToCart(CartItem cartItem, ShoppingCart shoppingCart) {
		boolean cartChanged = shoppingCart.addItem(cartItem);
		if (cartChanged)
			shoppingCartRepository.save(shoppingCart);
		return shoppingCart;
	}

	@Transactional
	@Override
	public ShoppingCart removeItemFromCart(CartItem cartItem, ShoppingCart shoppingCart) {
		boolean cartChanged = shoppingCart.removeItem(cartItem);
		if (cartChanged)
			shoppingCartRepository.save(shoppingCart);

		return shoppingCart;
	}

	@Transactional
	@Override
	public ShoppingCart clearCart(ShoppingCart shoppingCart) {
		return clearCart();
	}

	private ShoppingCart clearCart() {
		// TODO decide whether and how to persist clearing of cart
		return new ShoppingCart();
	}

}

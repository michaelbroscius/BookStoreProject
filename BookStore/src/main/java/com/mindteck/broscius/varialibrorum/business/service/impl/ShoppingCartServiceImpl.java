package com.mindteck.broscius.varialibrorum.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindteck.broscius.varialibrorum.business.service.ShoppingCartService;
import com.mindteck.broscius.varialibrorum.data.entity.CartItem;
import com.mindteck.broscius.varialibrorum.data.entity.Product;
import com.mindteck.broscius.varialibrorum.data.entity.ShoppingCart;
import com.mindteck.broscius.varialibrorum.data.entity.User;
import com.mindteck.broscius.varialibrorum.data.repository.CartItemRepository;
import com.mindteck.broscius.varialibrorum.data.repository.ProductRepository;
import com.mindteck.broscius.varialibrorum.data.repository.ShoppingCartRepository;
import com.mindteck.broscius.varialibrorum.data.repository.UserRepository;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	ShoppingCartRepository shoppingCartRepository;
	@Autowired
	CartItemRepository cartItemRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductRepository productRepository;

	@Transactional(readOnly = true)
	@Override
	public ShoppingCart getShoppingCartForUser(User user) {
		ShoppingCart shoppingCart = shoppingCartRepository.findByUser(user);
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			shoppingCart.setUser(user);
			shoppingCartRepository.save(shoppingCart);
		}
		System.out.println("ShoppingCartServiceImpl getShoppingCartForUser( " + user + " returning " + shoppingCart);
		return shoppingCart;
	}

	@Transactional(readOnly = true)
	@Override
	public ShoppingCart getShoppingCart(Long id) {
		return shoppingCartRepository.findOne(id);
	}

	@Transactional
	@Override
	public ShoppingCart addItemToCart(CartItem cartItem, ShoppingCart shoppingCart) {
		cartItemRepository.save(cartItem);
		boolean cartChanged = shoppingCart.addItem(cartItem);
		if (cartChanged)
			shoppingCartRepository.save(shoppingCart);
		return shoppingCart;
	}

	@Transactional
	@Override
	public ShoppingCart addItemToUserCart(User user, Long productId, Integer quantity) {
		ShoppingCart shoppingCart = getShoppingCartForUser(user);
		Product product = productRepository.findOne(productId);
		CartItem cartItem = new CartItem(product, quantity);
		
		System.out.println("ShoppingCartService addItemToUserCart adding: cartItem " + cartItem + "and shoppingCart "
				+ shoppingCart);
		addItemToCart(cartItem, shoppingCart);

		return addItemToCart(cartItem, shoppingCart);
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

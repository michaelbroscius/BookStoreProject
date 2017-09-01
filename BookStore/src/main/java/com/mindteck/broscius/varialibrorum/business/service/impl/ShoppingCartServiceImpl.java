package com.mindteck.broscius.varialibrorum.business.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private final Logger logger = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

	@Autowired
	ShoppingCartRepository shoppingCartRepository;

	@Autowired
	CartItemRepository cartItemRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProductRepository productRepository;

	@Transactional
	@Override
	public ShoppingCart getShoppingCartForUser(User user) {
		logger.debug("Entered getShoppingCartForUser({}).", user);
		ShoppingCart shoppingCart = shoppingCartRepository.findByUser(user);
		logger.debug("getShoppingCartForUser shoppingCartRepository.findByUser() returned {}.", shoppingCart);
		if (shoppingCart == null) {
			logger.debug("Creating new cart.");
			shoppingCart = new ShoppingCart();
			shoppingCart.setUser(user);
			shoppingCartRepository.save(shoppingCart);
		}
		logger.debug("getShoppingCartForUser({}) returning shoppingCart:{}. ", user, shoppingCart);
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
		logger.debug("Entered addItemToUserCart({}, {}, {}.", user, productId, quantity);
		ShoppingCart shoppingCart = getShoppingCartForUser(user);
		Product product = productRepository.findOne(productId);
		CartItem cartItem = cartItemRepository.findByProduct(product);
		logger.debug("cartItem:{}.", cartItem);
		if (cartItem == null) {
			logger.debug("Creating new cartItem for: {}, quantity: {}.", product, quantity);
			cartItem = new CartItem(product, quantity);
		}

		logger.info("Added {} to {} for {}.", cartItem, shoppingCart, user);

		addItemToCart(cartItem, shoppingCart);
		logger.debug("addItemToUserCart() returning {}, {}.", cartItem, shoppingCart);
		return addItemToCart(cartItem, shoppingCart);
	}

	@Transactional
	@Override
	public ShoppingCart addItemToUserCart(CartItem cartItem, User user) {
		logger.debug("Entered addItemToUserCart(User {}, CartItem {})", user, cartItem);
		ShoppingCart shoppingCart = getShoppingCartForUser(user);
		shoppingCart = addItemToCart(cartItem, shoppingCart);
		return shoppingCart;
	}

	@Transactional
	@Override
	public ShoppingCart addQuantityOfItemToUserCart(int quantity, Long cartID, User user) {
		CartItem cartItem = cartItemRepository.findOne(cartID);
		if (cartItem == null) {
			logger.error("addQuantitityOfItemToUserCart(): no cart with ID: {}.", cartID);
			throw new IllegalArgumentException("No cart with ID: " + cartID);
		}
		cartItem.setQuantity(quantity);
		return addItemToUserCart(cartItem, user);
	}

	@Transactional
	@Override
	public ShoppingCart removeItemFromCart(CartItem cartItem, ShoppingCart shoppingCart) {
		logger.debug("Entered removeItemFromCart({}, {},).", cartItem, shoppingCart);
		boolean cartChanged = shoppingCart.removeItem(cartItem);
		if (cartChanged) {
			shoppingCartRepository.save(shoppingCart);
			logger.info("Removed {} from {} for {}", cartItem, shoppingCart.getCart(), shoppingCart.getUser());
		}

		logger.debug("removeItemFromCart() returning {}", shoppingCart);
		return shoppingCart;
	}

	@Transactional
	@Override
	public ShoppingCart clearCart(ShoppingCart shoppingCart) {
		logger.info("Clearing cart:{}.", shoppingCart);
		for (CartItem cartItem : shoppingCart.getCart()) {
			cartItemRepository.delete(cartItem);
		}
		shoppingCart.clearCart();
		shoppingCartRepository.save(shoppingCart);
		return shoppingCart;
	}

	@Transactional
	@Override
	public ShoppingCart saveCart(ShoppingCart shoppingCart) {

		return shoppingCartRepository.save(shoppingCart);
	}

}

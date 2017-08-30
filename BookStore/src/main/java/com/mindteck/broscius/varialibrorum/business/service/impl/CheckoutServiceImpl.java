package com.mindteck.broscius.varialibrorum.business.service.impl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindteck.broscius.varialibrorum.business.service.CheckoutService;
import com.mindteck.broscius.varialibrorum.business.service.ProductService;
import com.mindteck.broscius.varialibrorum.business.service.ShoppingCartService;
import com.mindteck.broscius.varialibrorum.data.entity.Order;
import com.mindteck.broscius.varialibrorum.data.entity.OrderItem;
import com.mindteck.broscius.varialibrorum.data.entity.Product;
import com.mindteck.broscius.varialibrorum.data.entity.ShoppingCart;
import com.mindteck.broscius.varialibrorum.data.entity.User;
import com.mindteck.broscius.varialibrorum.data.repository.CartItemRepository;
import com.mindteck.broscius.varialibrorum.data.repository.OrderRepository;
import com.mindteck.broscius.varialibrorum.data.repository.ShoppingCartRepository;
import com.mindteck.broscius.varialibrorum.data.repository.UserRepository;

@Service
public class CheckoutServiceImpl implements CheckoutService {
	private final Logger logger = LoggerFactory.getLogger(CheckoutServiceImpl.class);

	@Autowired
	ShoppingCartService shoppingCartService;

	@Autowired
	ProductService productService;

	@Autowired
	ShoppingCartRepository shoppingCartRepository;

	@Autowired
	CartItemRepository cartItemRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	OrderRepository orderRepository;

	@Transactional(readOnly = true)
	@Override
	public Order getOrderForUser(User user) {
		Order order = orderRepository.findByUser(user);
		logger.debug("getOrderForUser(user={}) returning Order={}.", user, order);
		return order;
	}

	@Transactional(readOnly = true)
	@Override
	public Order getShoppingCart(Long id) {
		return orderRepository.findOne(id);
	}

	@Transactional
	@Override
	public Order checkout(Order order, User user) {
		logger.debug("Entered checkout(order, user). order: {}, user:{}.", order, user);
		ShoppingCart shoppingCart = shoppingCartRepository.findByUser(user);
		logger.debug("shoppingCart:{}.", shoppingCart);

		order.setUser(user);
		order.setDate(LocalDate.now());
		order = orderRepository.save(order); // save order to generate ID
		order.setOrderNumber(order.obfuscateID());
		order = fillOrderFromShoppingCart(order, shoppingCart);
		orderRepository.save(order);
		shoppingCartService.clearCart(shoppingCart);

		logger.debug("After filling order  user: {}, shoppingCart: {}, order: {}.", user, shoppingCart, order);
		logger.debug("checkout returning {}", order);
		return order;
	}

	@Transactional
	protected Order fillOrderFromShoppingCart(Order order, ShoppingCart shoppingCart) {
		Set<OrderItem> orderItems = new HashSet<>();
		shoppingCart.getCart().stream().forEach(cartItem -> {
			Product product = cartItem.getProduct();
			// reduce stock of ordered item by ordered amount
			productService.reduceStock(cartItem.getQuantity(), product);
			OrderItem orderItem = new OrderItem(product.getId(), product.getName(), product.getPrice(),
					cartItem.getQuantity(), false);
			orderItems.add(orderItem);
		});
		order.setItems(orderItems);
		order = shipOrder(order);

		return order;
	}

	@Transactional
	protected Order shipOrder(Order order) {
		for (OrderItem orderItem : order.getItems()) {
			orderItem.setShipped(true);
		}
		shipItems(order);

		return order;
	}

	protected void shipItems(Order order) {

	}

}

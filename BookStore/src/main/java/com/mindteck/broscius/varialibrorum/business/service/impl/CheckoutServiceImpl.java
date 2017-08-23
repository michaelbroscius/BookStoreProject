package com.mindteck.broscius.varialibrorum.business.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindteck.broscius.varialibrorum.business.service.CheckoutService;
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

	@Autowired
	ShoppingCartService shoppingCartService;
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
		System.out.println("\nCheckoutService getOrderForUser(user=" + user + ") returning Order=" + order + ".");
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

		ShoppingCart shoppingCart = shoppingCartRepository.findByUser(user);
		System.out.println("\n CheckoutServiceImpl user: " + user + "\n checking out." + "\n shoppingCart: "
				+ shoppingCart + "\n order: " + order + " before checkout.");

		order.setUser(user);
		order = fillOrderFromShoppingCart(order, shoppingCart);
		orderRepository.save(order);
		shoppingCartService.clearCart(shoppingCart);

		System.out.println("\n CheckoutServiceImpl user: " + user + "\n checking out." + "\n shoppingCart: "
				+ shoppingCart + "\n order: " + order + ".");

		return order;
	}

	@Transactional
	protected Order fillOrderFromShoppingCart(Order order, ShoppingCart shoppingCart) {
		Set<OrderItem> orderItems = new HashSet<>();
		shoppingCart.getCart().stream().forEach(cartItem -> {
			Product product = cartItem.getProduct();
			OrderItem orderItem = new OrderItem(product.getId(), product.getPrice(), cartItem.getQuantity(), false);
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

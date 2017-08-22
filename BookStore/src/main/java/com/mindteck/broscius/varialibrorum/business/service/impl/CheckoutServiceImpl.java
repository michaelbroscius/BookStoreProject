package com.mindteck.broscius.varialibrorum.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindteck.broscius.varialibrorum.business.service.CheckoutService;
import com.mindteck.broscius.varialibrorum.data.entity.Order;
import com.mindteck.broscius.varialibrorum.data.entity.ShoppingCart;
import com.mindteck.broscius.varialibrorum.data.entity.User;
import com.mindteck.broscius.varialibrorum.data.repository.CartItemRepository;
import com.mindteck.broscius.varialibrorum.data.repository.OrderRepository;
import com.mindteck.broscius.varialibrorum.data.repository.ShoppingCartRepository;
import com.mindteck.broscius.varialibrorum.data.repository.UserRepository;

@Service
public class CheckoutServiceImpl implements CheckoutService {

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
	public Order checkout(User user) {
		// TODO Auto-generated method stub

		Order order = getOrderForUser(user);
		ShoppingCart shoppingCart = shoppingCartRepository.findByUser(user);

		System.out.println("\n CheckoutServiceImpl user: " + user + "\n checking out." + "\n shoppingCart: "
				+ shoppingCart + "\n order: " + order + ".");

		return order;
	}

}

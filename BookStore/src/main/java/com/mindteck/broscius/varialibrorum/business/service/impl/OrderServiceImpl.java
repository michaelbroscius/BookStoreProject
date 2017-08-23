package com.mindteck.broscius.varialibrorum.business.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindteck.broscius.varialibrorum.business.service.OrderService;
import com.mindteck.broscius.varialibrorum.data.entity.Order;
import com.mindteck.broscius.varialibrorum.data.entity.User;
import com.mindteck.broscius.varialibrorum.data.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRepository;

	@Transactional
	@Override
	public List<Order> getOrdersForUser(User user) {

		return orderRepository.findAllByUser(user);
	}

}

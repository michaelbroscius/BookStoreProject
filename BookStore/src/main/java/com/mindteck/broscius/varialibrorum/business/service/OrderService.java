package com.mindteck.broscius.varialibrorum.business.service;

import java.util.List;

import com.mindteck.broscius.varialibrorum.data.entity.Order;
import com.mindteck.broscius.varialibrorum.data.entity.User;

/**
 * @author Michael Broscius August, 2017
 *
 */

public interface OrderService {

	List<Order> getOrdersForUser(User user);

}
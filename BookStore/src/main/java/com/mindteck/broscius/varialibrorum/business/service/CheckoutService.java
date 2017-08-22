package com.mindteck.broscius.varialibrorum.business.service;

import com.mindteck.broscius.varialibrorum.data.entity.Order;
import com.mindteck.broscius.varialibrorum.data.entity.User;

/**
 * @author Michael Broscius August, 2017
 *
 */

public interface CheckoutService {

	Order getOrderForUser(User user);

	Order getShoppingCart(Long id);
	
	Order checkout(User user);

}
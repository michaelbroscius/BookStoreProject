package com.mindteck.broscius.varialibrorum.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindteck.broscius.varialibrorum.data.entity.CartItem;
import com.mindteck.broscius.varialibrorum.data.entity.Product;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	CartItem findByProduct(Product product);

}

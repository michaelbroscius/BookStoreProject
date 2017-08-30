package com.mindteck.broscius.varialibrorum.business.service;

import java.util.List;

import com.mindteck.broscius.varialibrorum.data.entity.Product;

public interface ProductService {
	Product addStock(int quantity, Product product);

	Product reduceStock(int quantity, Product product);

	List<Product> findOutOfStockProducts();
	
}
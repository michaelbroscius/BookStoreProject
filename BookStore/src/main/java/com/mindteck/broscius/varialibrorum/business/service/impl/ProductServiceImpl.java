package com.mindteck.broscius.varialibrorum.business.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindteck.broscius.varialibrorum.business.service.ProductService;
import com.mindteck.broscius.varialibrorum.data.entity.Product;
import com.mindteck.broscius.varialibrorum.data.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	ProductRepository productRepository;

	@Transactional
	@Override
	public Product addStock(int quantity, Product product) {
		logger.debug("Entered addStock(int {}, Product {}).", quantity, product);
		product.updateStock(quantity);
		productRepository.save(product);
		logger.info("Added {} to stock of {}", quantity, product);
		logger.debug("addStock() returning {}.", product);
		return product;
	}

	@Transactional
	@Override
	public Product reduceStock(int quantity, Product product) {
		logger.debug("Entered reduceStock(int {}, Product {})", quantity, product);
		if (quantity > product.getNumberInStock()) {
			logger.warn("Tried to reduce stock of {} by {} which is more than the number in stock: {}.", product,
					quantity, product.getNumberInStock());
			throw new IllegalArgumentException(
					"quantity " + quantity + " greater than number of " + product + " stock");
		}
		product.updateStock(-quantity);
		productRepository.save(product);
		logger.info("Reduced stock of {} by {}", product.getName(), quantity);
		logger.debug("reductStock() returning {}.", product);
		return product;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Product> findOutOfStockProducts() {

		return productRepository.findByNumberInStockLessThanEqual(0);
	}
}

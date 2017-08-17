package com.mindteck.broscius.varialibrorum.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindteck.broscius.varialibrorum.data.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}

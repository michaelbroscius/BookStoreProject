package com.mindteck.broscius.varialibrorum.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.mindteck.broscius.varialibrorum.data.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);
}

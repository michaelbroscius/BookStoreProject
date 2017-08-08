package com.mindteck.broscius.varialibrorum.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindteck.broscius.varialibrorum.business.service.UserService;
import com.mindteck.broscius.varialibrorum.data.entity.User;
import com.mindteck.broscius.varialibrorum.data.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	@Override
	public boolean add(User user) {
		boolean isNew = false;
		if (!userRepository.exists(user.getId())) {
			userRepository.save(user);
			isNew = true;
		}
		return isNew;
	}

	@Transactional(readOnly = true)
	@Override
	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<>();
		Iterable<User> users = userRepository.findAll();
		users.forEach(user -> {
			userList.add(user);
		});

		return userList;
	}

	@Transactional(readOnly = true)
	@Override
	public User getUser(String id) {
		User user = userRepository.findOne(Long.parseLong(id));
		return user;
	}

	@Transactional(readOnly = true)
	@Override
	public User validateUser(String email, String password) throws AuthenticationException {
		System.out.println("UserServiceImpl.validateUser entered.");

		if (email == null || password == null || email.equals("") || password.equals("")) {
			System.out.println("UserServiceImpl.validateUser: nulls or blank strings passed.");
			throw new IllegalArgumentException("Null or empty argument");
		}
		User user = null;
		user = userRepository.findByEmail(email);
		if (null == user || !user.getPassword().equals(password)) {
			System.out.println("UserServiceImpl.validateUser: Invalid e-mail or password.");
			throw new AuthenticationException("Invalid e-mail or password");
		}
		
		//TODO remove debugging statement
		System.out.println("UserServiceImpl.validateUser: " + user);
		return user;
	}

	@Transactional(readOnly = true)
	@Override
	public User getUser(Long id) {
		return userRepository.findOne(id);
	}

}

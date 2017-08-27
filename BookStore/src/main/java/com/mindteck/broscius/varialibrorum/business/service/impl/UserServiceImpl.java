package com.mindteck.broscius.varialibrorum.business.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindteck.broscius.varialibrorum.business.service.UserService;
import com.mindteck.broscius.varialibrorum.data.entity.Role;
import com.mindteck.broscius.varialibrorum.data.entity.User;
import com.mindteck.broscius.varialibrorum.data.form.UserManagementForm;
import com.mindteck.broscius.varialibrorum.data.form.UserRegistrationForm;
import com.mindteck.broscius.varialibrorum.data.repository.RoleRepository;
import com.mindteck.broscius.varialibrorum.data.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Transactional
	@Override
	public User add(User user) {

		return userRepository.save(user);
	}

	@Transactional
	@Override
	public User add(UserRegistrationForm userRegistrationForm) {
		User user = new User();
		user.setName(userRegistrationForm.getName());
		user.setEmail(userRegistrationForm.getEmail());
		user.setPassword(userRegistrationForm.getPassword());
		Role role = roleRepository.findByName("user");
		logger.debug("In add(userRegistrationForm): role={}", role);
		user.setRole(role);

		return userRepository.save(user);
	}

	@Transactional
	@Override
	public User add(UserManagementForm userManagementForm) {
		User user = new User();
		user.setName(userManagementForm.getName());
		user.setEmail(userManagementForm.getEmail());
		user.setPassword(userManagementForm.getPassword());
		user.setRole(roleRepository.findByName("user"));

		return userRepository.save(user);

	}

	@Transactional(readOnly = true)
	@Override
	public List<User> getAllUsers() {

		return userRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public User getUser(String id) {
		User user = userRepository.findOne(Long.parseLong(id));
		return user;
	}

	@Transactional(readOnly = true)
	@Override
	public User getUser(Long id) {
		return userRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	@Override
	public User validateUser(String email, String password) throws AuthenticationException {
		logger.debug("validateUser() entered.");

		if (email == null || password == null || email.equals("") || password.equals("")) {
			logger.debug("validateUser(): nulls or blank strings passed.");
			throw new IllegalArgumentException("Null or empty argument");
		}
		User user = null;
		user = userRepository.findByEmail(email);
		if (null == user || !user.getPassword().equals(password)) {
			logger.debug("validateUser(): Invalid e-mail or password.");
			throw new AuthenticationException("Invalid e-mail or password");
		}

		logger.debug("validateUser() returning {}.", user);
		return user;
	}
}

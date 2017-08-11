package com.mindteck.broscius.varialibrorum.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindteck.broscius.varialibrorum.business.service.UserService;
import com.mindteck.broscius.varialibrorum.data.entity.Role;
import com.mindteck.broscius.varialibrorum.data.entity.User;
import com.mindteck.broscius.varialibrorum.data.repository.RoleRepository;
import com.mindteck.broscius.varialibrorum.data.repository.UserRepository;
import com.mindteck.broscius.varialibrorum.web.form.UserManagementForm;
import com.mindteck.broscius.varialibrorum.web.form.UserRegistrationForm;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	//TODO find out how and implement autowired on constructor with two beans
/*	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRespository) {
		this.userRepository = userRepository;
	}*/

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
//		Role role = roleRepository.findOne(1l);
		System.out.println("UserServiceImpl.add(userRegistrationForm): role=" + role);
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

		// TODO change debugging statements to logging
		System.out.println("UserServiceImpl.validateUser: " + user);
		return user;
	}

	@Transactional(readOnly = true)
	@Override
	public User getUser(Long id) {
		return userRepository.findOne(id);
	}

}

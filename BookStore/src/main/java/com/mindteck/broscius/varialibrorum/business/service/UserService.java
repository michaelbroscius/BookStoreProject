package com.mindteck.broscius.varialibrorum.business.service;

import java.util.List;

import com.mindteck.broscius.varialibrorum.business.service.impl.AuthenticationException;
import com.mindteck.broscius.varialibrorum.data.entity.User;
import com.mindteck.broscius.varialibrorum.data.form.UserManagementForm;
import com.mindteck.broscius.varialibrorum.data.form.UserRegistrationForm;

public interface UserService {

	/**
	 * 
	 * @param user
	 * @return user added
	 */
	User add(User user);

	/**
	 * 
	 * @param userForm
	 * @return user persisted
	 */
	User add(UserRegistrationForm userForm);

	User add(UserManagementForm userManagementForm);

	/**
	 * 
	 * @param email
	 * @param password
	 * @return User with email and password if valid, otherwise null
	 * @throws AuthenticationException
	 * 
	 */
	User validateUser(String email, String password) throws AuthenticationException;

	User getUser(String id);

	User getUser(Long id);

	List<User> getAllUsers();

}

package com.mindteck.broscius.varialibrorum.business.service;

import java.util.List;

import com.mindteck.broscius.varialibrorum.business.service.impl.AuthenticationException;
import com.mindteck.broscius.varialibrorum.data.entity.User;

public interface UserService {
	
	/**
	 * 
	 * @param user
	 * @return true if user added
	 */
	boolean add(User user);
	
	/**	
	 *   
	 * @param email
	 * @param password
	 * @return User with email and password if valid, otherwise null
	 * @throws AuthenticationException 
	 * 
	 */
	public User validateUser(String email, String password) throws AuthenticationException;
	
	public User getUser(String id);	
	
	public User getUser(Long id);
	List<User> getAllUsers();

}

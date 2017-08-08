package com.mindteck.broscius.varialibrorum.data.webservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindteck.broscius.varialibrorum.data.entity.User;
import com.mindteck.broscius.varialibrorum.data.repository.UserRepository;

@RestController
public class UserRestController {
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	List<User> findAll(@RequestParam(required = false) String userEmail) {
		List<User> users = new ArrayList<>();
		if (null == userEmail) {
			Iterable<User> results = this.userRepository.findAll();
			results.forEach(room -> {
				users.add(room);
			});

		} else {
			User user = this.userRepository.findByEmail(userEmail);
			if (null != user) {
				users.add(user);
			}
		}
		return users;
	}

}

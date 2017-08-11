package com.mindteck.broscius.varialibrorum.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.mindteck.broscius.varialibrorum.data.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByName(String name);

}

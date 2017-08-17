package com.mindteck.broscius.varialibrorum.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindteck.broscius.varialibrorum.data.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}

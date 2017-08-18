package com.mindteck.broscius.varialibrorum.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindteck.broscius.varialibrorum.data.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

}

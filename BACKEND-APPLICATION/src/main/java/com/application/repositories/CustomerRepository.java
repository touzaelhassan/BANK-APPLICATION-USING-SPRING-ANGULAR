package com.application.repositories;

import com.application.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("customerRepositoryBean")
public interface CustomerRepository extends JpaRepository<Customer, Integer> { }

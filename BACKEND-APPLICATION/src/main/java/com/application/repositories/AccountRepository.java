package com.application.repositories;

import com.application.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("accountRepositoryBean")
public interface AccountRepository extends JpaRepository<Account, Integer> { }

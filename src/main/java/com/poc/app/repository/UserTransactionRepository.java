package com.poc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.app.model.UserTransaction;

public interface UserTransactionRepository extends JpaRepository<UserTransaction, Long>{
	
}

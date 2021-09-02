package com.poc.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.app.model.User;

import java.util.List;

public interface UserRepository  extends JpaRepository<User, Long> {

    List<User> findByEmailId(String emailId);
}

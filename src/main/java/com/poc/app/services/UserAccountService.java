package com.poc.app.services;

import java.util.List;

import com.poc.app.model.User;
import com.poc.app.model.UserTransaction;

public interface UserAccountService {

	public User saveUserAccount(User user) ;
	
	public List<User> findByEmailId(String emailId); 
	
	public Iterable<UserTransaction> findAllTransactions();
	
	public User transferCredit(String fromEmail,String toEmail, double trfAmt);
	
}

package com.poc.app.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.poc.app.exception.UserNotFoundException;
import com.poc.app.model.User;
import com.poc.app.model.UserTransaction;
import com.poc.app.repository.UserRepository;
import com.poc.app.repository.UserTransactionRepository;

@Service
public class UserAccountServiceImpl implements UserAccountService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserTransactionRepository userTransactionRepository;
	
	@Value("${app.credit}")
	private double initalCredit;

	@Override
	public User saveUserAccount(User user) {
		user.setAccountBal(initalCredit);
		return userRepository.save(user);
	}

	@Override
	public List<User> findByEmailId(String emailId) {
		return userRepository.findByEmailId(emailId);
	}

	@Override
	public Iterable<UserTransaction> findAllTransactions() {
		return userTransactionRepository.findAll();
	}
	
	@Override
	public User transferCredit(String fromEmail, String toEmail, double trfAmt) throws UserNotFoundException{
		User user;
		if (fromEmail == null && toEmail == null) {
			throw new UserNotFoundException();
		}
		List<User> user1 = userRepository.findByEmailId(fromEmail);
		if (user1.isEmpty()) {

			throw new UserNotFoundException();
		} else {
			user = user1.get(0);
			user.setAccountBal(user.getAccountBal() - trfAmt);
		}

		List<User> user2 = userRepository.findByEmailId(toEmail);
		if (user2.isEmpty()) {
			throw new UserNotFoundException();
		} else {
			user = user2.get(0);
			user.setAccountBal(user.getAccountBal() + trfAmt);
		}
		UserTransaction userTrans=new UserTransaction();
		userTrans.setFromAccount(fromEmail);
		userTrans.setToAccount(toEmail);
		userTrans.setTrfAmount(trfAmt);
		userTrans.setTransactionType("transfer");
		userTrans.setTransactionDate(new Date(System.currentTimeMillis()));
		userTransactionRepository.save(userTrans);
		return user;
	}

}

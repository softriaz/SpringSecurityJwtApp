package com.poc.app.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.poc.app.exception.UserNotFoundException;
import com.poc.app.model.User;
import com.poc.app.model.UserTransaction;
import com.poc.app.repository.UserRepository;
import com.poc.app.repository.UserTransactionRepository;
import com.poc.app.security.AuthenticationRequest;
import com.poc.app.security.AuthenticationResponse;
import com.poc.app.services.UserAccountService;
import com.poc.app.util.JwtUtil;
import com.poc.app.util.MyUserDetailsService;

@RestController
@RequestMapping("/api/user")
public class PocApiController {
	
	@Autowired
	private UserAccountService userAccountService;


	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@RequestBody User user) {
		return userAccountService.saveUserAccount(user);
	}

	@GetMapping("/balance/{emailId}")
	public List<User> findByEmail(@PathVariable String emailId) {
		return userAccountService.findByEmailId(emailId);
	}

	@PutMapping("/transfer")
	public User transferAmount(@RequestBody UserTransaction userTran) {
		return userAccountService.transferCredit(userTran.getFromAccount(), userTran.getToAccount(), userTran.getTrfAmount());
	}

	@GetMapping("/transactions")
	public Iterable<UserTransaction> findUserTransactions() {
		return userAccountService.findAllTransactions();
	}

}

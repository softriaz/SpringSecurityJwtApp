package com.poc.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserTransaction {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long transId;
	
	@Column(nullable = false)
	private double trfAmount;
	
	@Column(nullable = false)
	private String fromAccount;
	
	@Column(nullable = false)
	private String toAccount;
	
	@Column(nullable = false)
	private String transactionType;
	
	@Column(nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
	private java.sql.Date transactionDate;
	
	public long getTransId() {
		return transId;
	}

	public void setTransId(long transId) {
		this.transId = transId;
	}

	public double getTrfAmount() {
		return trfAmount;
	}

	public void setTrfAmount(double trfAmount) {
		this.trfAmount = trfAmount;
	}

	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public java.sql.Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(java.sql.Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
}

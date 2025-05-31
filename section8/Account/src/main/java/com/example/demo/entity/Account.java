package com.example.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Schema(name = "Accounts", description = "Account Creation")
@Entity
public class Account extends BaseEntity {
	@Schema(description = "Unique ID of the customer", example = "101")
	private Long customerId;
	@Id
	private Long accountNumber;
	private String accountType;
	private String branchAddress;

	public Account() {
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	@Override
	public String toString() {
		return "Account [customerId=" + customerId + ", accountNumber=" + accountNumber + ", accountType=" + accountType
				+ ", branchAddress=" + branchAddress + "]";
	}

	public Account(Long customerId, Long accountNumber, String accountType, String branchAddress) {

		this.customerId = customerId;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.branchAddress = branchAddress;
	}

}

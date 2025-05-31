package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Schema(name = "Accounts", description = "Customer Creation")

public class AccountDto {
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digit")
	private Long accountNumber;

	@Schema(description = "Account Type", example = "SAVINGS")
	@NotEmpty(message = "Account Type can not be empty or null")
	private String accountType;

	@Schema(description = "Branch Address", example = "123,New York,India")
	@NotEmpty(message = "Branch Address can not be empty or null")
	private String branchAddress;

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

}

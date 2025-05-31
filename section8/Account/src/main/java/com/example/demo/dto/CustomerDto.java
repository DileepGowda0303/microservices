package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CustomerDto {

	@NotEmpty(message = "Name can not be a empty or empty")
	@Size(min = 5, max = 30, message = "The length of customer should be 5 to 30 ")
	private String name;

	@NotEmpty(message = "Name can not be a empty or empty")
	@Email(message = "Email address shold be valid value")
	private String email;

	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digit")
	private String mobileNumber;

	private AccountDto accountDto;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public AccountDto getAccountDto() {
		return accountDto;
	}

	public void setAccountDto(AccountDto accountDto) {
		this.accountDto = accountDto;
	}

	@Override
	public String toString() {
		return "CustomerDto [name=" + name + ", email=" + email + ", mobileNumber=" + mobileNumber + ", accountDto="
				+ accountDto + "]";
	}

	public CustomerDto() {

	}

}

package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CustomerDto;

@Service
public interface IAccountService {

	public void createAccount(CustomerDto customerDto);

	public CustomerDto fetchAccount(String mobileNumber);

	public boolean updateAccount(CustomerDto customerDto);

	public boolean deleteAccount(String mobileNumber);
}

package com.example.demo.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constant.AccountConstant;
import com.example.demo.dto.AccountDto;
import com.example.demo.dto.CustomerDto;
import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.exceptions.CustomerAlredyExistException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public void createAccount(CustomerDto customerDto) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDto, customer);
		Optional<Customer> response = customerRepo.findByMobileNumber(customer.getMobileNumber());
		if (response.isPresent()) {
			throw new CustomerAlredyExistException(
					"Customer Already Registerd with given mobile number " + customer.getMobileNumber());
		}
		Customer savedCustomer = customerRepo.save(customer);
		Account account = createNewAccount(savedCustomer);
		accountRepo.save(account);
	}

	private Account createNewAccount(Customer customer) {
		Account newCount = new Account();
		newCount.setCustomerId(customer.getCustomerId());
		long randomAccount = 10000000L + new Random().nextInt(90000000);
		newCount.setAccountNumber(randomAccount);
		newCount.setAccountType(AccountConstant.SAVINGS);
		newCount.setBranchAddress(AccountConstant.Address);
		return newCount;

	}

	@Override
	public CustomerDto fetchAccount(String mobileNumber) {
		Customer customer = customerRepo.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber));
		Account account = accountRepo.findByCustomerId(customer.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("Account", "CustomerId", customer.getCustomerId().toString()));

		CustomerDto customerDto = new CustomerDto();
		BeanUtils.copyProperties(customer, customerDto);
		AccountDto accountDto = new AccountDto();
		BeanUtils.copyProperties(account, accountDto);
		customerDto.setAccountDto(accountDto); // set the initialized object
		return customerDto;
	}

	@Override
	public boolean updateAccount(CustomerDto customerDto) {
		boolean isUpdated = false;
		if (customerDto.getAccountDto() != null) {
			Account account = accountRepo.findById(customerDto.getAccountDto().getAccountNumber())
					.orElseThrow(() -> new ResourceNotFoundException("Account", "Account Number",
							customerDto.getAccountDto().getAccountNumber().toString()));
			BeanUtils.copyProperties(customerDto.getAccountDto(), account);
			accountRepo.save(account);
			Long customerId = account.getCustomerId();
			Customer customer = customerRepo.findById(customerId).orElseThrow(
					() -> new ResourceNotFoundException("Customer", "CustomerNumber ", customerId.toString()));
			BeanUtils.copyProperties(customerDto, customer);
			customerRepo.save(customer);
			isUpdated = true;
		}
		return isUpdated;
	}

	@Override
	public boolean deleteAccount(String mobileNumber) {
		Customer customer = customerRepo.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber));
		accountRepo.deleteByCustomerId(customer.getCustomerId());
		customerRepo.deleteById(customer.getCustomerId());
		return true;
	}

}

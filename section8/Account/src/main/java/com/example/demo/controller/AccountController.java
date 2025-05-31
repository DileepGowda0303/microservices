package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constant.AccountConstant;
import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.IAccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Validated
@RefreshScope
@RequestMapping(path = "/api/v1", produces = { MediaType.APPLICATION_JSON_VALUE })
@Tag(name = "Accounts", description = "Operations related to Accounts")
public class AccountController {
	@Autowired
	private IAccountService accountService;

	@PostMapping("/createAccount")
	@Operation(summary = "Add a new Customer", description = "Creates a new Customer record")
	@ApiResponse(responseCode = "200", description = "Customer Created ")
	public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto request) {
		accountService.createAccount(request);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountConstant.STATUS_201, AccountConstant.MESSAGE_201));
	}

	@GetMapping("/getAccountDetails")
	public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber) {
		CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(customerDto);
	}

	@Operation(summary = "Update account details")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error") })
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateAccountDetails(@RequestBody CustomerDto request) {

		boolean updated = accountService.updateAccount(request);
		if (updated) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseDto(AccountConstant.STATUS_500, AccountConstant.MESSAGE_500));

	}

	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam String mobileNumber) {
		boolean deleted = accountService.deleteAccount(mobileNumber);
		if (deleted) {

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseDto(AccountConstant.STATUS_500, AccountConstant.MESSAGE_500));
	}

	@Value("${accounts.message}")
	String txt;

	@GetMapping("get-status")
	public String getDataFromPropertiesFile() {

		return txt;
	}

}

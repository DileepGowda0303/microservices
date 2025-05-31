package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constant.LoanConstant;
import com.example.demo.dto.ErrorResponseDto;
import com.example.demo.dto.LoansContactInfoDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.ILoanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@Validated
public class LoanController {
	 
	private ILoanService iLoanService;

	public LoanController(ILoanService iLoansService) {
		this.iLoanService = iLoansService;
	}

	//@Value("${build.version}")
	private String buildVersion;

	@Autowired
	private Environment environment;

	/*
	 * @Autowired private LoansContactInfoDto loansContactInfoDto;
	 */

	@Operation(summary = "Create Loan REST API", description = "REST API to create new loan inside EazyBank")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createLoan(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
		iLoanService.createLoan(mobileNumber);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(LoanConstant.STATUS_201, LoanConstant.MESSAGE_201));
	}

	/*
	 * @GetMapping("/contact-info") public ResponseEntity<LoansContactInfoDto>
	 * getContactInfo() { return
	 * ResponseEntity.status(HttpStatus.OK).body(loansContactInfoDto); }
	 */

	@GetMapping("/build-info")
	public ResponseEntity<String> getBuildInfo() {
		return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
	}

}

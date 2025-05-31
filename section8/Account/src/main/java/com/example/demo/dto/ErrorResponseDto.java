package com.example.demo.dto;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;

public class ErrorResponseDto {

	private String apiPath;
	private HttpStatus errorCode;
	private String errorMessage;
	private LocalDate errorTime;

	public String getApiPath() {
		return apiPath;
	}

	public void setApiPath(String apiPath) {
		this.apiPath = apiPath;
	}

	public HttpStatus getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public LocalDate getErrorTime() {
		return errorTime;
	}

	public void setErrorTime(LocalDate errorTime) {
		this.errorTime = errorTime;
	}

	public ErrorResponseDto(String apiPath, HttpStatus errorCode, String errorMessage, LocalDate errorTime) {

		this.apiPath = apiPath;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorTime = errorTime;
	}

	public ErrorResponseDto() {

	}

}

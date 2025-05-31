package com.example.demo.exceptions;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalException {
	public ResponseEntity<Object> HandlingMethodArgumentNotvalid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode httpStatusCode, WebRequest webRequest) {
		Map<String, String> validationError = new HashMap<>();
		List<ObjectError> validatorsErrorList = ex.getBindingResult().getAllErrors();
		validatorsErrorList.forEach(err -> {
			String fieldName = ((FieldError) err).getField();
			String validationMessage = err.getDefaultMessage();
			validationError.put(fieldName, validationMessage);
		});
		return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handlingGlobalExcepton(Exception exception, WebRequest webRequest) {
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false),
				HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), LocalDate.now());
		return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CustomerAlredyExistException.class)
	public ResponseEntity<ErrorResponseDto> handledResourcesNotFoundException(CustomerAlredyExistException exception,
			WebRequest webRequest) {
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.NOT_FOUND,
				exception.getMessage(), LocalDate.now());
		return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handledCustomerAlreadyExistsException(ResourceNotFoundException exception,
			WebRequest webRequest) {
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false),
				HttpStatus.BAD_REQUEST, exception.getMessage(), LocalDate.now());
		return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
	}
}

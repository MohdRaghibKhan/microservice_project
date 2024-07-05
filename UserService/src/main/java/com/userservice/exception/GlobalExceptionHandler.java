package com.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.userservice.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundEXception.class)
	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundEXception exception){
		 String message = exception.getMessage();
		ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
		 return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
}

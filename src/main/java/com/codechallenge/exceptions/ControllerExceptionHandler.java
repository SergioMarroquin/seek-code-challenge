package com.codechallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.codechallenge.controllers.response.Response;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	private Response<String> buildControllerResponse(String exceptionMessage) {
		return new Response<>(exceptionMessage);
	}
	
	@ExceptionHandler(ClientNotFoundException.class)
	public ResponseEntity<Response<String>> clientNotFoundException(ClientNotFoundException exception) {
		return new ResponseEntity<>(buildControllerResponse(exception.getExceptionMessage()), HttpStatus.NOT_FOUND);
	}

}
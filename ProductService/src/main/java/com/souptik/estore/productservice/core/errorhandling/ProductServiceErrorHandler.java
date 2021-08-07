package com.souptik.estore.productservice.core.errorhandling;

import java.util.Date;

import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.queryhandling.QueryExecutionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductServiceErrorHandler {

	@ExceptionHandler(value = { IllegalStateException.class })
	public ResponseEntity<Object> handleException(IllegalStateException exception) {
		return new ResponseEntity<>(new ErrorMessage(new Date(), exception.getMessage()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { CommandExecutionException.class })
	public ResponseEntity<Object> handleException(CommandExecutionException exception) {
		return new ResponseEntity<>(new ErrorMessage(new Date(), exception.getMessage()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = { QueryExecutionException.class })
	public ResponseEntity<Object> handleException(QueryExecutionException exception) {
		return new ResponseEntity<>(new ErrorMessage(new Date(), exception.getMessage()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//blanket exception handler
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleException(Exception exception) {
		return new ResponseEntity<>(new ErrorMessage(new Date(), exception.getMessage()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

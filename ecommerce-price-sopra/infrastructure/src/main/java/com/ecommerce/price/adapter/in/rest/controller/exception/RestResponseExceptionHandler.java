package com.ecommerce.price.adapter.in.rest.controller.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.ecommerce.price.adapter.in.rest.controller.exception.model.RestError;
import com.ecommerce.price.exception.ProductoNotFoundException;

@ControllerAdvice
public class RestResponseExceptionHandler { 

	private static final String ERROR_INTERNO = "Error";
	
	@ExceptionHandler(NoHandlerFoundException.class)
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex) {
		return new ResponseEntity<>(new  RestError(ERROR_INTERNO, HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(), ex.getMessage()),
				new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
//	@ExceptionHandler(NoResourceFoundException.class)
//	protected ResponseEntity<Object> handleNoResourceFoundException(NoHandlerFoundException ex) {
//		return new ResponseEntity<>(new  RestError(ERROR_INTERNO, HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(), ex.getMessage()),
//				new HttpHeaders(), HttpStatus.NOT_FOUND);
//	}
	
	@ExceptionHandler(ProductoNotFoundException.class)
	protected ResponseEntity<Object> handleProductNotFound(ProductoNotFoundException ex) {
		return new ResponseEntity<>(new  RestError(ERROR_INTERNO, HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value(), ex.getMessage()),
				new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleRuntimeError(Exception ex) {	
		
		if(ex instanceof NoResourceFoundException) {
			return new ResponseEntity<>(new  RestError(ERROR_INTERNO, HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(), ex.getMessage()),
					new HttpHeaders(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new  RestError(ERROR_INTERNO, HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value(), ex.getMessage().split(";")[0]),
				new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}

/**
 * 
 */
package com.rsouza01.waesscalableweb.controller;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rsouza01.waesscalableweb.dto.ErrorDetails;
import com.rsouza01.waesscalableweb.exception.TransactionNotFoundException;

/**
 * @author rsouza
 *
 * Reference: https://dzone.com/articles/implementing-validation-for-restful-services-with
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

		ErrorDetails errorDetails = 
				new ErrorDetails(
						new Date(), 
						ex.getMessage(),
						request.getDescription(false));
		
		return new ResponseEntity<Object>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	  
	@Override
	  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	      HttpHeaders headers, HttpStatus status, WebRequest request) {
		
	    ErrorDetails errorDetails = 
	    		new ErrorDetails(
	    		new Date(), "Validation Failed",
	    		ex.getBindingResult().toString());
	    
	    return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	

	  @ExceptionHandler(TransactionNotFoundException.class)
	  public final ResponseEntity<Object> handleTransactionNotFoundException(TransactionNotFoundException ex, WebRequest request) {

		  ErrorDetails errorDetails = new ErrorDetails(
				  new Date(), ex.getMessage(),
				  request.getDescription(false));
	    
		  return new ResponseEntity<Object>(errorDetails, HttpStatus.NOT_FOUND);
	  }	
}

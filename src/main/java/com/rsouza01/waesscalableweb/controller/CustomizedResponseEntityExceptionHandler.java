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
import com.rsouza01.waesscalableweb.exception.TransactionIncompleteException;
import com.rsouza01.waesscalableweb.exception.TransactionNotFoundException;
import com.rsouza01.waesscalableweb.exception.InvalidJSONFormatException;

/**
 * Customized validation scheme for API inputs.
 * 
 * @author Rodrigo Souza (rsouza01)
 *
 * @see <a href="https://dzone.com/articles/implementing-validation-for-restful-services-with">Implementing Validation for RESTful Services With Spring Boot</a>
 * 
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handles exceptions not covered by the other methods
	 * @param ex Exception thrown
	 * @param request the request
	 * @return A response entity with the info
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

		ErrorDetails errorDetails = 
				new ErrorDetails(
						new Date(), 
						ex.getMessage(),
						request.getDescription(false));

		return new ResponseEntity<Object>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	/**
	 * Handles invalid inputs
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorDetails errorDetails = 
				new ErrorDetails(
						new Date(), "Validation Failed",
						ex.getBindingResult().toString());

		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	}


	/**
	 * Handles invalid Difference requests (only one panel)
	 * @param ex Exception thrown
	 * @param request the request
	 * @return A response entity with the info
	 */
	@ExceptionHandler(TransactionIncompleteException.class)
	public final ResponseEntity<Object> handleTransactionIncompleteException(TransactionIncompleteException ex, WebRequest request) {

		ErrorDetails errorDetails = new ErrorDetails(
				new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<Object>(errorDetails, HttpStatus.UNPROCESSABLE_ENTITY);
	}	

	/**
	 * Handles invalid Difference requests (only one panel)
	 * @param ex Exception thrown
	 * @param request the request
	 * @return A response entity with the info
	 */
	@ExceptionHandler(InvalidJSONFormatException.class)
	public final ResponseEntity<Object> handleInvalidJSONFormatException(InvalidJSONFormatException ex, WebRequest request) {

		ErrorDetails errorDetails = new ErrorDetails(
				new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	}	

	/**
	 * Handles invalid Difference requests (transaction not found)
	 * @param ex Exception thrown
	 * @param request the request
	 * @return A response entity with the info
	 */
	@ExceptionHandler(TransactionNotFoundException.class)
	public final ResponseEntity<Object> handleTransactionNotFoundException(TransactionNotFoundException ex, WebRequest request) {

		ErrorDetails errorDetails = new ErrorDetails(
				new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<Object>(errorDetails, HttpStatus.NOT_FOUND);
	}	
}
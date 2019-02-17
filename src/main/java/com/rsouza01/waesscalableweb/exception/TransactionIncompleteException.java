package com.rsouza01.waesscalableweb.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

/**
 * Handles invalid difference requests (only one panel)
 * 
 * @author Rodrigo Souza (rsouza01)
 *
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class TransactionIncompleteException extends RuntimeException {
	
	/** Serial Version UID */
	private static final long serialVersionUID = 8457944544096855607L;

	/**
	 * Message constructor
	 * @param message the message
	 */
	public TransactionIncompleteException(String message) { super(message); }
}

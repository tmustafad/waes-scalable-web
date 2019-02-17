package com.rsouza01.waesscalableweb.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

/**
 * Handles invalid Difference requests (transaction not found)
 * @author Rodrigo Souza (rsouza01)
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TransactionNotFoundException extends RuntimeException {
	
	/** Serial Version UID */
	private static final long serialVersionUID = -1671833531728881764L;

	/**
	 * Message constructor
	 * @param message the message
	 */
	public TransactionNotFoundException(String message) { super(message); }
}

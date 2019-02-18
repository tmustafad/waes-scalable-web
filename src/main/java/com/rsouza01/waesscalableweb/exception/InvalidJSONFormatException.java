package com.rsouza01.waesscalableweb.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

/**
 * Handles invalid JSON format
 * @author Rodrigo Souza (rsouza01)
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidJSONFormatException extends RuntimeException {
	

	/** Serial Version UID */
	private static final long serialVersionUID = 2539628606312630531L;

	/**
	 * Message constructor
	 * @param message the message
	 */
	public InvalidJSONFormatException(String message) { super(message); }
}

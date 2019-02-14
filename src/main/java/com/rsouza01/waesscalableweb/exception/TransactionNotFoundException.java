/**
 * 
 */
package com.rsouza01.waesscalableweb.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * @author rsouza
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TransactionNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8457944544096855607L;

	/**
	 * @param arg0
	 */
	public TransactionNotFoundException(String arg0) {
		super(arg0);
	}

	
}

package com.rsouza01.waesscalableweb.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Simple DTO for Exception handling by {@link CustomizedResponseEntityExceptionHandler}
 *  
 * @author Rodrigo Souza (rsouza01)
 *
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ErrorDetails {
	
	/**
	 * Timestamp when the error ocurred.
	 */
	private Date timestamp;
	
	/**
	 * Error message.
	 */
	private String message;
	
	/**
	 * Error details.
	 */
	private String details;

}
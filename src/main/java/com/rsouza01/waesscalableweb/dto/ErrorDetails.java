/**
 * 
 */
package com.rsouza01.waesscalableweb.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @author rsouza
 *
 */

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ErrorDetails {
	
	private Date timestamp;
	private String message;
	private String details;

}
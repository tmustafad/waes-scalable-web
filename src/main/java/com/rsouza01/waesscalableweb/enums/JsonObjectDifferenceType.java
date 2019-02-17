/**
 * 
 */
package com.rsouza01.waesscalableweb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum with the status for a key searching inside a JSON.
 * 
 * @author Rodrigo Souza (rsouza01)
 *
 */
@AllArgsConstructor @Getter
public enum JsonObjectDifferenceType {
	
	/** The key was not found */
    KEY_NOT_FOUND,

    /** The key was found but the values do not match */
    KEY_FOUND_VALUE_UNMATCHED
}

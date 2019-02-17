package com.rsouza01.waesscalableweb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum with all the possible results from a Difference GET request.
 * 
 * @author Rodrigo Souza (rsouza01)
 *
 */
@AllArgsConstructor @Getter
public enum JsonContentsResult {
	
	/** Panels with equal contents */
    EQUAL_CONTENTS,
    
	/** Panels with different sizes and contents  */
    DIFFERENT_SIZES_CONTENTS,

	/** Panels with same sizes but different contents  */
    SAME_SIZES_BUT_DIFFERENT_CONTENTS
}

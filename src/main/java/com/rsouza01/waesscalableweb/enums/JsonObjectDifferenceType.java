/**
 * 
 */
package com.rsouza01.waesscalableweb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public enum JsonObjectDifferenceType {
    KEY_NOT_FOUND,
    KEY_FOUND_VALUE_UNMATCHED
}

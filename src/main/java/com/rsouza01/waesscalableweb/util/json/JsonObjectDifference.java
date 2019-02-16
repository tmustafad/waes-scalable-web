/**
 * 
 */
package com.rsouza01.waesscalableweb.util.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;

import com.rsouza01.waesscalableweb.enums.JsonObjectDifferenceType;
/**
 * @author rsouza
 *
 */
@Getter @Setter
public class JsonObjectDifference {

	private Logger logger = LoggerFactory.getLogger(JsonObjectDifference.class);
	
	private String key;
	
	private String objectValue;

	private String objectComparedValue;
	
	private JsonObjectDifferenceType type;

	/**
	 * @param type
	 * @param key
	 * @param value
	 */
	public JsonObjectDifference(JsonObjectDifferenceType type, String key, String objectValue, String objectComparedValue) {
		this.key = key;
		this.objectValue = objectValue;
		this.objectComparedValue = objectComparedValue;	
	}
	/**
	 * @param type
	 * @param key
	 */
	public JsonObjectDifference(JsonObjectDifferenceType type, String key) {
		this.type = type;
		this.key = key;
	}
}

/**
 * 
 */
package com.rsouza01.waesscalableweb.util.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.rsouza01.waesscalableweb.enums.JsonObjectDifferenceType;
/**
 * @author rsouza
 *
 */
@NoArgsConstructor @Getter @Setter
public class JsonObjectDifference {

	private String key;
	
	private String objectValue;

	private String objectComparedValue;
	
	private JsonObjectDifferenceType type;

	
	/**
	 * @param type
	 * @param key
	 * @param value
	 */
	public JsonObjectDifference(JsonObjectDifference copied) {
		this.key = copied.getKey();
		this.objectValue = copied.getObjectValue();
		this.objectComparedValue = copied.getObjectComparedValue();
		this.type = copied.getType();
	}
	
	/**
	 * @param type
	 * @param key
	 */
	public JsonObjectDifference(JsonObjectDifferenceType type, String key) {
		this.type = type;
		this.key = key;
	}
	/**
	 * @param key
	 * @param objectValue
	 * @param objectComparedValue
	 * @param type
	 */
	public JsonObjectDifference(String key, String objectValue, String objectComparedValue,
			JsonObjectDifferenceType type) {
		super();
		this.key = key;
		this.objectValue = objectValue;
		this.objectComparedValue = objectComparedValue;
		this.type = type;
	}

	/**
	 * @param keyFoundValueUnmatched
	 * @param key2
	 * @param innerKeyValue
	 * @param comparableKeyValue
	 */
	public JsonObjectDifference(JsonObjectDifferenceType type, String key, String objectValue, String objectComparedValue) {
		this.type = type;
		this.key = key;
		this.objectValue = objectValue;
		this.objectComparedValue = objectComparedValue;
	}
}

/**
 * 
 */
package com.rsouza01.waesscalableweb.util.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.rsouza01.waesscalableweb.enums.JsonObjectDifferenceType;

/**
 * Class that represents a difference between the same key.
 * 
 * @author Rodrigo Souza (rsouza01)
 *
 */
@NoArgsConstructor @Getter @Setter
public class JsonObjectDifference {

	/** JSON key */
	private String key;
	
	/** Value compared (source object) */
	private String objectValue;

	/** Value compared (target object) */
	private String objectComparedValue;
	
	/** Type of difference */
	private JsonObjectDifferenceType type;


	/**
	 * Copy Constructor
	 * 
	 * @param copied the object copied from
	 */
	public JsonObjectDifference(JsonObjectDifference copied) {
		this.key = copied.getKey();
		this.objectValue = copied.getObjectValue();
		this.objectComparedValue = copied.getObjectComparedValue();
		this.type = copied.getType();
	}

	/**
	 * Constructor
	 * @param type Type of difference
	 * @param key JSON key
	 */
	public JsonObjectDifference(JsonObjectDifferenceType type, String key) {
		this.type = type;
		this.key = key;
	}

	/**
	 * Constructor
	 * 
	 * @param key JSON key
	 * @param objectValue Value compared (source object)
	 * @param objectComparedValue Value compared (target object)
	 * @param type Type of difference
	 */
	public JsonObjectDifference(String key, String objectValue, String objectComparedValue,
			JsonObjectDifferenceType type) {
		super();
		this.key = key;
		this.objectValue = objectValue;
		this.objectComparedValue = objectComparedValue;
		this.type = type;
	}
}

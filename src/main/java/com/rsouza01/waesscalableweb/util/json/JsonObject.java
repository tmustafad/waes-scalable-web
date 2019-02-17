package com.rsouza01.waesscalableweb.util.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.rsouza01.waesscalableweb.enums.JsonObjectDifferenceType;

/**
 * Object that holds a JSON file. 
 * 
 * Extended from {@code JSONObject}, comparison functionalities added.
 * 
 * @author Rodrigo Souza (rsouza01)
 *
 */
public class JsonObject extends JSONObject {

	/** Path init for JsonPath*/
	private static final String PATH_INIT = "$.";


	/**
	 * Constructor which receives a JSON string.
	 * @param json the JSON string
	 * @throws JSONException if the string is not in a valid JSON format.
	 */
	public JsonObject(String json) throws JSONException { super(json); }

	
	/**
	 * Compares the instance object with another {@code JSONObject}.
	 * @param comparableJsonObject the target JSOJObject
	 * @return A list with {@code JsonObjectDifference} instances.
	 */
	public List<JsonObjectDifference> getDifferencesWith(JSONObject comparableJsonObject) {

		return getDifferencesWith(this, comparableJsonObject, null);
	}
	
	
	/**
	 * Auxiliary method to convert numeric types into Strings.
	 * @param obj JSON field
	 * @return the field converted to a String
	 */
	private static String castJsonObjectToString(Object obj) {

		if (obj instanceof Integer) {
			return Integer.toString((int)obj);
	    }
	    else if (obj instanceof Float) {
			return Float.toString((int)obj);
	    }
	    else {
	    	return (String)obj;
	    }		
	}
	
	/**
	 * Recursive method to compute differences between a source Object ({@code innerJsonObject}) 
	 * and a target object ({@code comparableJsonObject}). 
	 * @param innerJsonObject the source object.
	 * @param comparableJsonObject the target object.
	 * @param parent the parent object used to keep track of the JSON tree, used for the JsonPath object. 
	 * @return a list with differences between the source and the target objects.
	 */
	private List<JsonObjectDifference> getDifferencesWith(
			final JSONObject innerJsonObject, 
			final JSONObject comparableJsonObject, 
			String parent) {

		
		parent = parent == null? JsonObject.PATH_INIT : parent;

		/** The list with differences for the iteration */
		List<JsonObjectDifference> differences = 
				new ArrayList<JsonObjectDifference>();

		/** Getting the keys */
		Iterator<String> keys = innerJsonObject.keys();
		
		while(keys.hasNext()) {
			
		    String key = keys.next();
		    
		    /** Mounting the key for the JsonPath */
		    String compositeKey = parent.equals(JsonObject.PATH_INIT) ? JsonObject.PATH_INIT + key : parent + "." + key;
		    
		    
		    /** If the object is a composite object, calls the method recursivelly */
		    if (innerJsonObject.get(key) instanceof JSONObject) {

		    	differences.addAll(
		    			getDifferencesWith((JSONObject)innerJsonObject.get(key), 
		    			comparableJsonObject, 
		    			compositeKey));      
		    } else {

		    	String comparableKeyValue = "";
		    	
		    	try {
		    		/** Is there the key on the target object? */
			    	comparableKeyValue = 
			    			JsonObject.castJsonObjectToString(
			    					JsonPath.read(comparableJsonObject.toString(), compositeKey));
			    	
				} catch (PathNotFoundException e) {
					/** No, there is not... */
					comparableKeyValue = null;
				}

		    	if(comparableKeyValue != null && !comparableKeyValue.isEmpty()) {
		    		
		    		String innerKeyValue = "";
		    		
		    		try {
		    			/** Gets the instance value */
		    			innerKeyValue = 
		    					JsonObject.castJsonObjectToString(innerJsonObject.get(key)); 

		    			/** Compares the source value with the target value. */
			    		if(!innerKeyValue.equals(comparableKeyValue)) {
				    		differences.add(
				    				new JsonObjectDifference(
				    						key,
				    						innerKeyValue, 
				    						comparableKeyValue,
				    						JsonObjectDifferenceType.KEY_FOUND_VALUE_UNMATCHED 
				    						));
			    		}
			    		
					} catch (Exception e) {
			    		differences.add(
			    				new JsonObjectDifference(
			    						key, 
			    						innerKeyValue, 
			    						comparableKeyValue,			    						
			    						JsonObjectDifferenceType.KEY_FOUND_VALUE_UNMATCHED
			    						));
					}
		    	} else {
		    		differences.add(new JsonObjectDifference(JsonObjectDifferenceType.KEY_NOT_FOUND, key));
		    	}
		    }
		}
		
		return differences;
	}
}
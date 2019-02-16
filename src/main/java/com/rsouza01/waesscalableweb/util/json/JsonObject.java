/**
 * 
 */
package com.rsouza01.waesscalableweb.util.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.rsouza01.waesscalableweb.enums.JsonObjectDifferenceType;


/**
 * @author rsouza
 *
 */
public class JsonObject extends JSONObject {

	private static final String PATH_INIT = "$.";

	private Logger logger = LoggerFactory.getLogger(JsonObject.class);

	/**
	 * @param json
	 * @throws JSONException
	 */
	public JsonObject(String json) throws JSONException {
		super(json);
	}

	
	public List<JsonObjectDifference> getDifferencesWith(JSONObject comparableJsonObject) {

		return getDifferencesWith(this, comparableJsonObject, null);
	}
	
	
	private static String castJsonObjectToString(Object obj) {

		if (obj instanceof Integer) {
			return Integer.toString((int)obj);
	    }
	    else {
	    	return (String)obj;
	    }		
	}
	
	private List<JsonObjectDifference> getDifferencesWith(
			final JSONObject innerJsonObject, 
			final JSONObject comparableJsonObject, 
			String parent) {

		
		parent = parent == null? JsonObject.PATH_INIT : parent;
		
		List<JsonObjectDifference> differences = 
				new ArrayList<JsonObjectDifference>();

		Iterator<String> keys = innerJsonObject.keys();
		
		while(keys.hasNext()) {
			
		    String key = keys.next();
		    
		    logger.debug(String.format("%s = %s", key, innerJsonObject.get(key)));
		    
		    String compositeKey = parent.equals(JsonObject.PATH_INIT) ? JsonObject.PATH_INIT + key : parent + "." + key;
		    
		    if (innerJsonObject.get(key) instanceof JSONObject) {

		    	differences.addAll(
		    			getDifferencesWith((JSONObject)innerJsonObject.get(key), 
		    			comparableJsonObject, 
		    			compositeKey));      
		    } else {

		    	String comparableKeyValue = "";
		    	
		    	try {
		    		
			    	comparableKeyValue = 
			    			JsonObject.castJsonObjectToString(
			    					JsonPath.read(comparableJsonObject.toString(), compositeKey));
			    	
				} catch (PathNotFoundException e) {
				    logger.debug(String.format("Composite key %s not found.", compositeKey));
				}

		    	if(comparableKeyValue != null && !comparableKeyValue.isEmpty()) {
		    		
		    		String innerKeyValue = "";
		    		
		    		try {

		    			innerKeyValue = 
		    					JsonObject.castJsonObjectToString(innerJsonObject.get(key)); 
			    		
			    		if(!innerKeyValue.equals(comparableKeyValue)) {
				    		differences.add(
				    				new JsonObjectDifference(
				    						JsonObjectDifferenceType.KEY_FOUND_VALUE_UNMATCHED, 
				    						key, 
				    						innerKeyValue, 
				    						comparableKeyValue));
			    		}
			    		
					} catch (Exception e) {
			    		differences.add(
			    				new JsonObjectDifference(
			    						JsonObjectDifferenceType.KEY_FOUND_VALUE_UNMATCHED, 
			    						key, 
			    						innerKeyValue, 
			    						comparableKeyValue));
					}
		    	} else {
		    		differences.add(new JsonObjectDifference(JsonObjectDifferenceType.KEY_NOT_FOUND, key));
		    	}
		    }
		}
		
		return differences;
	}
}

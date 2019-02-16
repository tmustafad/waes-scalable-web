/**
 * 
 */
package com.rsouza01.waesscalableweb.util.json;

import lombok.AllArgsConstructor;
import org.json.JSONObject;

import com.rsouza01.waesscalableweb.enums.JsonContentsResult;

/**
 * @author rsouza
 *
 */
@AllArgsConstructor
public class JsonContentsComparator {
	
	private String leftJsonContent; 
	private String rightJsonContent; 

	
	public JsonContentsComparison compare() {
		
		if((leftJsonContent == null && rightJsonContent == null) ||
				leftJsonContent.equals(rightJsonContent)) {
			return new JsonContentsComparison(JsonContentsResult.EQUAL_CONTENTS);
		} 

		if(leftJsonContent != null && rightJsonContent != null 
				&& leftJsonContent.length() == rightJsonContent.length()
				&& !leftJsonContent.equals(rightJsonContent)) {
			return new JsonContentsComparison(JsonContentsResult.SAME_SIZES_BUT_DIFFERENT_CONTENTS);
			
		}
		
		JSONObject leftJsonObject = new JSONObject(leftJsonContent);
		JSONObject rightJsonObject = new JSONObject(rightJsonContent);

		
		return new JsonContentsComparison(JsonContentsResult.DIFFERENT_SIZES_CONTENTS);
	}
}

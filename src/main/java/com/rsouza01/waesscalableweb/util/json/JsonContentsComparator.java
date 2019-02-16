/**
 * 
 */
package com.rsouza01.waesscalableweb.util.json;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rsouza01.waesscalableweb.enums.JsonContentsResult;

/**
 * @author rsouza
 *
 */
public class JsonContentsComparator {
	
	private Logger logger = LoggerFactory.getLogger(JsonContentsComparator.class);

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
		
		JsonObject leftJsonObject = new JsonObject(leftJsonContent);
		JsonObject rightJsonObject = new JsonObject(rightJsonContent);


		List<JsonObjectDifference> leftDifferences = leftJsonObject.getDifferencesWith(rightJsonObject);
		List<JsonObjectDifference> rightDifferences = rightJsonObject.getDifferencesWith(leftJsonObject);

		return new JsonContentsComparison(JsonContentsResult.DIFFERENT_SIZES_CONTENTS, leftDifferences, rightDifferences);
	}



	/**
	 * @param leftJsonContent
	 * @param rightJsonContent
	 */
	public JsonContentsComparator(String leftJsonContent, String rightJsonContent) {
		super();
		this.leftJsonContent = leftJsonContent;
		this.rightJsonContent = rightJsonContent;
	}
}

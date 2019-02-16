/**
 * 
 */
package com.rsouza01.waesscalableweb.util.json;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

import com.rsouza01.waesscalableweb.enums.JsonContentsResult;
/**
 * @author rsouza
 *
 */
@Getter
public class JsonContentsComparison {
	
	private JsonContentsResult result;
	
	private List<JsonObjectDifference> leftDifferences = new ArrayList<>();
	private List<JsonObjectDifference> rightDifferences = new ArrayList<>();

	public JsonContentsComparison(JsonContentsResult result) {
		this.result = result; 
	}
	
	public JsonContentsComparison(JsonContentsResult result, List<JsonObjectDifference> leftDifferences, List<JsonObjectDifference> rightDifferences) {
		this.result = result; 
		this.leftDifferences = leftDifferences; 
		this.rightDifferences = rightDifferences; 
	}
	
	public boolean hasDifferences() {
		return (leftDifferences.size() + rightDifferences.size()) > 0;
	}
}

package com.rsouza01.waesscalableweb.util.json;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

import com.rsouza01.waesscalableweb.enums.JsonContentsResult;

/**
 * Object with the results of the comparison
 * 
 * @author Rodrigo Souza (rsouza01)
 *
 */
@Getter
public class JsonContentsComparison {
	
	/** The result of the compariosn */
	private JsonContentsResult result;
	
	/** Left panel differences.*/
	private List<JsonObjectDifference> leftDifferences = new ArrayList<>();

	/** Right panel differences.*/
	private List<JsonObjectDifference> rightDifferences = new ArrayList<>();

	/**
	 * Constructor 
	 * @param result the result object
	 */
	public JsonContentsComparison(JsonContentsResult result) {
		this.result = result; 
	}
	
	/**
	 * Constructor
	 * @param result the result object
	 * @param leftDifferences Left panel differences
	 * @param rightDifferences Right panel differences
	 */
	public JsonContentsComparison(JsonContentsResult result, List<JsonObjectDifference> leftDifferences, List<JsonObjectDifference> rightDifferences) {
		this.result = result; 
		this.leftDifferences = leftDifferences; 
		this.rightDifferences = rightDifferences; 
	}
	
	/**
	 * Method that informs if there are differences.
	 * @return if the comparison has differences.
	 */
	public boolean hasDifferences() {
		return (leftDifferences.size() + rightDifferences.size()) > 0;
	}
}

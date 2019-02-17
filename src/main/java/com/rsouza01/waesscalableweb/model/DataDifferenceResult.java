package com.rsouza01.waesscalableweb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.rsouza01.waesscalableweb.enums.JsonContentsResult;
import com.rsouza01.waesscalableweb.util.json.JsonObjectDifference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class DataDifferenceResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7535955528281487172L;

	protected String transactionId;

	protected JsonContentsResult result;
	
	protected List<JsonObjectDifference> leftDifferences = new ArrayList<>();
	protected List<JsonObjectDifference> rightDifferences = new ArrayList<>();


	/**
	 * @param result
	 * @param leftDifferences
	 * @param rightDifferences
	 */
	public DataDifferenceResult(String transactionId, JsonContentsResult result, List<JsonObjectDifference> leftDifferences,
			List<JsonObjectDifference> rightDifferences) {
		
		this.result = result;
		this.transactionId = transactionId;
		this.leftDifferences = leftDifferences;
		this.rightDifferences = rightDifferences;
	}

}

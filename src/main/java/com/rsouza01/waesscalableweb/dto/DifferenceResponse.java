package com.rsouza01.waesscalableweb.dto;

import com.rsouza01.waesscalableweb.enums.JsonContentsResult;
import com.rsouza01.waesscalableweb.model.DataDifferenceResult;
import com.rsouza01.waesscalableweb.util.json.JsonObjectDifference;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DifferenceResponse  {

	protected String transactionId;

	protected JsonContentsResult result;
	
	protected JsonObjectDifference[] leftDifferences;
	protected JsonObjectDifference[] rightDifferences;

	
	/**
	 * @param dataDifferenceResult
	 */
	public DifferenceResponse(DataDifferenceResult dataDifferenceResult) {
		this.result = dataDifferenceResult.getResult();
		this.transactionId = dataDifferenceResult.getTransactionId();

		
		if(dataDifferenceResult != null && dataDifferenceResult.getLeftDifferences() != null) {
			this.leftDifferences = dataDifferenceResult.getLeftDifferences().stream()
				    .map(JsonObjectDifference::new)
				    .toArray(JsonObjectDifference[]::new);
		}

		if(dataDifferenceResult != null && dataDifferenceResult.getRightDifferences() != null) {
			this.rightDifferences = dataDifferenceResult.getRightDifferences().stream()
				    .map(JsonObjectDifference::new)
				    .toArray(JsonObjectDifference[]::new);
		}
	}
}

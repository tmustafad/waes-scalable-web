package com.rsouza01.waesscalableweb.dto;

import com.rsouza01.waesscalableweb.enums.JsonContentsResult;
import com.rsouza01.waesscalableweb.model.DataDifferenceResult;
import com.rsouza01.waesscalableweb.util.json.JsonObjectDifference;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO used for returning the result of a Difference GET Request by {@code DataDifferenceApiRestController}.
 * 
 * @author Rodrigo Souza (rsouza01)
 *
 */
@Getter @Setter
public class DifferenceResponse  {

	/** The request Transaction ID*/
	protected String transactionId;

	/** Enum property with the result of the operation.
	 * @see {@code JsonContentsResult}.
	 */
	protected JsonContentsResult result;
	
	/**
	 * Object holding how different the right panel is from the left panel.
	 * @see {@code JsonObjectDifference}.
	 */
	protected JsonObjectDifference[] leftDifferences;

	/**
	 * Object holding how different the left panel is from the right panel.
	 * @see {@code JsonObjectDifference}.
	 */
	protected JsonObjectDifference[] rightDifferences;

	/**
	 * Constructor that builds a DifferenceResponse from a {@code DataDifferenceResult} 
	 * @param dataDifferenceResult
	 */
	public DifferenceResponse(DataDifferenceResult dataDifferenceResult) {
		
		this.result = dataDifferenceResult.getResult();
		this.transactionId = dataDifferenceResult.getTransactionId();

		
		/** This code converts ArrayLists to [] */
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

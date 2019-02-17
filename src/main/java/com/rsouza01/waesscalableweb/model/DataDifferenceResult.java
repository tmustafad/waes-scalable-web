package com.rsouza01.waesscalableweb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.rsouza01.waesscalableweb.enums.JsonContentsResult;
import com.rsouza01.waesscalableweb.util.json.JsonObjectDifference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class with the contents of a difference request. 
 * It holds the difference between two panels, as follows:
 * {@link DataDifferenceResult#leftDifferences} tells how different the right panel is from the left panel, and
 * {@link DataDifferenceResult#rightDifferences} tells how different the left panel is from the right panel.
 * 
 * @author Rodrigo Souza (rsouza01)
 *
 */
@Getter @Setter @NoArgsConstructor
public class DataDifferenceResult implements Serializable {

	/** Serial Version UID */
	private static final long serialVersionUID = 7535955528281487172L;

	/** The operation Transaction Id*/
	protected String transactionId;

	/** The result. @see {@code JsonContentsResult} */
	protected JsonContentsResult result;
	
	/** Descriptive differences found on the right panel from the left. @see {@code JsonObjectDifference} */
	protected List<JsonObjectDifference> leftDifferences = new ArrayList<>();

	/** Descriptive differences found on the left panel from the right. @see {@code JsonObjectDifference} */
	protected List<JsonObjectDifference> rightDifferences = new ArrayList<>();


	/**
	 * Constructor.
	 * 
	 * @param transactionId The operation Transaction Id
	 * @param result the difference result
	 * @param leftDifferences the differences found on the right panel from the left
	 * @param rightDifferences the differences found on the left panel from the right
	 */
	public DataDifferenceResult(String transactionId, JsonContentsResult result, List<JsonObjectDifference> leftDifferences,
			List<JsonObjectDifference> rightDifferences) {
		
		this.result = result;
		this.transactionId = transactionId;
		this.leftDifferences = leftDifferences;
		this.rightDifferences = rightDifferences;
	}

}

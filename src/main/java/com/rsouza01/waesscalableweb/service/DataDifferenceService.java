package com.rsouza01.waesscalableweb.service;

import com.rsouza01.waesscalableweb.enums.PanelSide;
import com.rsouza01.waesscalableweb.exception.TransactionIncompleteException;
import com.rsouza01.waesscalableweb.model.DataDifferenceEntry;
import com.rsouza01.waesscalableweb.model.DataDifferenceResult;

/**
 * DataDifferenceService interface definition.
 * 
 * It performs difference operations between two JSON-base64 formatted strings.
 * 
 * @author Rodrigo Souza (rsouza01)
 *
 */
public interface DataDifferenceService {

	/**
	 * Saves content for a specified side (left/right)
	 * @param transactionId the transaction id for the operation
	 * @param panelSide the panel side for the operation (left or right)
	 * @param input the panel contents
	 * @return the saved object
	 */
	DataDifferenceEntry inputData(String transactionId, PanelSide panelSide, String input);

	/**
	 * Gets the differences between two panels identified by a transaction id
	 * 
	 * @param transactionId the transaction id for the operation
	 * @return the contents of a difference request.
	 * @throws TransactionIncompleteException if not both panels are loaded.
	 */
	DataDifferenceResult difference(String transactionId) throws TransactionIncompleteException;
}

package com.rsouza01.waesscalableweb.service;

import com.rsouza01.waesscalableweb.enums.PanelSide;
import com.rsouza01.waesscalableweb.exception.TransactionIncompleteException;
import com.rsouza01.waesscalableweb.model.DataDifferenceEntry;
import com.rsouza01.waesscalableweb.model.DataDifferenceResult;

public interface DataDifferenceService {

	void inputData(String transactionId, PanelSide panelSide, String input);

	DataDifferenceResult difference(String transactionId) throws TransactionIncompleteException;
}

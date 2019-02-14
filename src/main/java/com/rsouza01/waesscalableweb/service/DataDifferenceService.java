package com.rsouza01.waesscalableweb.service;

import com.rsouza01.waesscalableweb.enums.PanelSide;
import com.rsouza01.waesscalableweb.exception.TransactionNotFoundException;
import com.rsouza01.waesscalableweb.model.DataDifferenceEntry;
import com.rsouza01.waesscalableweb.model.DataDifferenceResult;

public interface DataDifferenceService {

	void inputData(String transactionId, PanelSide panelSide, String input);

	void inputData(DataDifferenceEntry dataDifferenceEntry);

	DataDifferenceResult difference(String transactionId) throws TransactionNotFoundException;
}

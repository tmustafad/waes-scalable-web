package com.rsouza01.waesscalableweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsouza01.waesscalableweb.enums.PanelSide;
import com.rsouza01.waesscalableweb.exception.TransactionNotFoundException;
import com.rsouza01.waesscalableweb.model.DataDifferenceEntry;
import com.rsouza01.waesscalableweb.model.DataDifferenceResult;
import com.rsouza01.waesscalableweb.repository.DataDifferenceEntryRepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DataDifferenceServiceImpl implements DataDifferenceService {

	private Logger logger = LoggerFactory.getLogger(DataDifferenceServiceImpl.class);
	
	@Autowired
	private DataDifferenceEntryRepository dataDifferenceEntryRepository;

	@Override
	public void inputData(String id, PanelSide panelSide, String input) {
		this.inputData(new DataDifferenceEntry(id, panelSide, input));
	}

	@Override
	public void inputData(DataDifferenceEntry dataDifferenceEntry) {

		dataDifferenceEntryRepository.save(dataDifferenceEntry);

		final String logMessage = "Transaction %s: InputData: %s";
		
		logger.info(String.format(logMessage, dataDifferenceEntry.getId(), dataDifferenceEntry.getContent()));
	}	
	

	@Override
	public DataDifferenceResult difference(String transactionId) throws TransactionNotFoundException {
		
		final String logMessage = "Transaction %s: Difference requested.";
		
		logger.info(String.format(logMessage, transactionId));
		
		List<DataDifferenceEntry> entries = dataDifferenceEntryRepository.findByTransactionId(transactionId);

		if(entries.size() < 2) {
			throw new TransactionNotFoundException(
					String.format("Two panels are needed (%s found).", entries.size()));
		}
		
		
		return new DataDifferenceResult();
	}
}

package com.rsouza01.waesscalableweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsouza01.waesscalableweb.enums.PanelSide;
import com.rsouza01.waesscalableweb.exception.TransactionIncompleteException;
import com.rsouza01.waesscalableweb.exception.TransactionNotFoundException;
import com.rsouza01.waesscalableweb.model.DataDifferenceEntry;
import com.rsouza01.waesscalableweb.model.DataDifferenceResult;
import com.rsouza01.waesscalableweb.repository.DataDifferenceEntryRepository;
import com.rsouza01.waesscalableweb.util.json.JsonContentsComparator;

import java.util.Base64;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DataDifferenceServiceImpl implements DataDifferenceService {

	private Logger logger = LoggerFactory.getLogger(DataDifferenceServiceImpl.class);
	
	@Autowired
	private DataDifferenceEntryRepository dataDifferenceEntryRepository;

	@Override
	public void inputData(String transactionId, PanelSide side, String base64Content) {

		Optional<DataDifferenceEntry> optionalDataDifferenceEntry = 
				dataDifferenceEntryRepository.findById(transactionId);

		DataDifferenceEntry dataDifferenceEntry = null;
		
		if(optionalDataDifferenceEntry.isPresent()) {
			
			dataDifferenceEntry = optionalDataDifferenceEntry.get();
			
			if(side == PanelSide.left) dataDifferenceEntry.setLeftContent(base64Content);
			else dataDifferenceEntry.setRightContent(base64Content);
			
		} else {
			dataDifferenceEntry = new DataDifferenceEntry(transactionId, side, base64Content);
		}
		
		dataDifferenceEntryRepository.save(dataDifferenceEntry);

		final String logMessage = "Transaction %s: InputData: left:'%s', right:'%s'";
		
		logger.info(String.format(
				logMessage, dataDifferenceEntry.getTransactionId(),
				dataDifferenceEntry.getLeftContent(), dataDifferenceEntry.getRightContent()));
	}	
	

	@Override
	public DataDifferenceResult difference(String transactionId) throws TransactionIncompleteException {
		
		final String logMessage = "Transaction %s: Difference requested.";
		
		logger.info(String.format(logMessage, transactionId));
		
		Optional<DataDifferenceEntry> optionalDataDifferenceEntry = 
				dataDifferenceEntryRepository.findById(transactionId);
		
		DataDifferenceEntry dataDifferenceEntry = null;
		
		if(optionalDataDifferenceEntry.isPresent()) {
			
			dataDifferenceEntry = optionalDataDifferenceEntry.get();
			
			if("".equals(dataDifferenceEntry.getLeftContent()) 
					|| "".equals(dataDifferenceEntry.getRightContent())
					|| dataDifferenceEntry.getLeftContent() == null 
					|| dataDifferenceEntry.getRightContent() == null ) {
				
				throw new TransactionIncompleteException("There are not enough content to compare.");
			}
			
		} else {
			throw new TransactionNotFoundException("No transaction found for the transactionId provided");
		}


		/** We are good to go.*/

		String leftPanelJSON = 
				new String(Base64.getDecoder().decode(dataDifferenceEntry.getLeftContent()));
		String rightPanelJSON = 
				new String(Base64.getDecoder().decode(dataDifferenceEntry.getRightContent()));
		
		JsonContentsComparator jsonContentsComparator = 
				new JsonContentsComparator(leftPanelJSON, rightPanelJSON);

		return new DataDifferenceResult(jsonContentsComparator);
	}
}

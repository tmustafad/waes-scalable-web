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
import com.rsouza01.waesscalableweb.util.json.JsonContentsComparison;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DataDifferenceServiceImpl implements DataDifferenceService {

	private Logger logger = LoggerFactory.getLogger(DataDifferenceServiceImpl.class);
	
	@Autowired
	private DataDifferenceEntryRepository dataDifferenceEntryRepository;

	@Override
	public void inputData(String transactionId, PanelSide side, String base64Content) {

		DataDifferenceEntry dataDifferenceEntry = 
				dataDifferenceEntryRepository
					.findById(transactionId)
					.orElse(new DataDifferenceEntry(transactionId));

		if(side == PanelSide.left) dataDifferenceEntry.setLeftContent(base64Content);
		else dataDifferenceEntry.setRightContent(base64Content);
			
		dataDifferenceEntryRepository.save(dataDifferenceEntry);

		logger.info(String.format(
				"Transaction %s: InputData: left:'%s', right:'%s'", dataDifferenceEntry.getTransactionId(),
				dataDifferenceEntry.getLeftContent(), dataDifferenceEntry.getRightContent()));
	}	
	

	@Override
	public DataDifferenceResult difference(String transactionId) throws TransactionIncompleteException {
		
		logger.info(String.format("Transaction %s: Difference requested.", transactionId));
		
		DataDifferenceEntry dataDifferenceEntry = 
				dataDifferenceEntryRepository.findById(transactionId)
				.orElseThrow(() -> 
					new TransactionNotFoundException("No transaction found for the transactionId provided"));

		if("".equals(dataDifferenceEntry.getLeftContent()) 
				|| "".equals(dataDifferenceEntry.getRightContent())
				|| dataDifferenceEntry.getLeftContent() == null 
				|| dataDifferenceEntry.getRightContent() == null ) {
			
			throw new TransactionIncompleteException("There are not enough content to compare.");
		}

		/** Basic tests performed. We are good to go .*/

		JsonContentsComparator jsonContentsComparator = 
				new JsonContentsComparator(
						new String(Base64.getDecoder().decode(dataDifferenceEntry.getLeftContent())), 
						new String(Base64.getDecoder().decode(dataDifferenceEntry.getRightContent())));
		
		JsonContentsComparison jsonContentsComparison = 
				jsonContentsComparator.compare();

		
		return new DataDifferenceResult(
				transactionId,
				jsonContentsComparison.getResult(), 
				jsonContentsComparison.getLeftDifferences(), 
				jsonContentsComparison.getRightDifferences());
	}
}
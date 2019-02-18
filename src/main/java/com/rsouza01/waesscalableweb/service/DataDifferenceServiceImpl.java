package com.rsouza01.waesscalableweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsouza01.waesscalableweb.enums.PanelSide;
import com.rsouza01.waesscalableweb.exception.InvalidJSONFormatException;
import com.rsouza01.waesscalableweb.exception.TransactionIncompleteException;
import com.rsouza01.waesscalableweb.exception.TransactionNotFoundException;
import com.rsouza01.waesscalableweb.model.DataDifferenceEntry;
import com.rsouza01.waesscalableweb.model.DataDifferenceResult;
import com.rsouza01.waesscalableweb.repository.DataDifferenceEntryRepository;
import com.rsouza01.waesscalableweb.util.json.JsonContentsComparator;
import com.rsouza01.waesscalableweb.util.json.JsonContentsComparison;
import com.rsouza01.waesscalableweb.util.json.JsonObject;

import java.util.Base64;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the {@code DataDifferenceService} interface.
 * 
 * It performs difference operations between two JSON-base64 formatted strings.
 * 
 * @author Rodrigo Souza (rsouza01)
 *
 */
@Service
public class DataDifferenceServiceImpl implements DataDifferenceService {
	
	
	/** Class logger */
	private Logger logger = LoggerFactory.getLogger(DataDifferenceServiceImpl.class);

	/** Data difference entry repository */
	@Autowired
	private DataDifferenceEntryRepository dataDifferenceEntryRepository;

	
	/**
	 * Test if the string is a valid JSON
	 * @param content
	 * @return
	 */
	private boolean isContentValid(String base64Content) {

		try {
			String jsonContent = 
					new String(Base64.getDecoder().decode(base64Content));
			
			JsonObject json = new JsonObject(jsonContent);
			
			return json != null;
			
		} catch (JSONException e) {
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.rsouza01.waesscalableweb.service.DataDifferenceService#inputData(java.lang.String, com.rsouza01.waesscalableweb.enums.PanelSide, java.lang.String)
	 */
	@Override
	public DataDifferenceEntry inputData(String transactionId, PanelSide side, String base64Content) {

		if(!isContentValid(base64Content)) {
			throw new InvalidJSONFormatException("The content is not in a valid JSON format.");
		}

		/** Either gets the entity from the persistence layer or creates a new one */
		DataDifferenceEntry dataDifferenceEntry = 
				dataDifferenceEntryRepository
					.findById(transactionId)
					.orElse(new DataDifferenceEntry(transactionId));

		/** Tests which side we are saving. */
		if(side == PanelSide.left) dataDifferenceEntry.setLeftContent(base64Content);
		else dataDifferenceEntry.setRightContent(base64Content);
			
		/** Saves the data. */
		DataDifferenceEntry dataDifferenceEntrySaved = dataDifferenceEntryRepository.save(dataDifferenceEntry);

		logger.info(String.format(
				"Transaction %s: InputData: left:'%s', right:'%s'", dataDifferenceEntry.getTransactionId(),
				dataDifferenceEntry.getLeftContent(), dataDifferenceEntry.getRightContent()));
		
		return dataDifferenceEntrySaved;
	}	
	

	/* (non-Javadoc)
	 * @see com.rsouza01.waesscalableweb.service.DataDifferenceService#difference(java.lang.String)
	 */
	@Override
	public DataDifferenceResult difference(String transactionId) throws TransactionIncompleteException {
		
		logger.info(String.format("Transaction %s: Difference requested.", transactionId));
		
		/** Try to find the entry on the persistence layer */
		DataDifferenceEntry dataDifferenceEntry = 
				dataDifferenceEntryRepository.findById(transactionId)
				.orElseThrow(() -> 
					new TransactionNotFoundException("No transaction found for the transactionId provided"));

		/** Performs some basic checks */
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
		
		/** Lets compare the contents */
		JsonContentsComparison jsonContentsComparison = 
				jsonContentsComparator.compare();

		
		return new DataDifferenceResult(
				transactionId,
				jsonContentsComparison.getResult(), 
				jsonContentsComparison.getLeftDifferences(), 
				jsonContentsComparison.getRightDifferences());
	}
}
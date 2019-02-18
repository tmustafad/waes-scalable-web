package com.rsouza01.waesscalableweb.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Base64;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.rsouza01.waesscalableweb.WaesScalableWebApplicationConstants;
import com.rsouza01.waesscalableweb.enums.JsonContentsResult;
import com.rsouza01.waesscalableweb.exception.TransactionIncompleteException;
import com.rsouza01.waesscalableweb.exception.TransactionNotFoundException;
import com.rsouza01.waesscalableweb.model.DataDifferenceEntry;
import com.rsouza01.waesscalableweb.model.DataDifferenceResult;
import com.rsouza01.waesscalableweb.repository.DataDifferenceEntryRepository;
import com.rsouza01.waesscalableweb.service.DataDifferenceService;

@RunWith(SpringRunner.class)
@WebMvcTest(DataDifferenceService.class)
public class DataDifferenceServiceTests {

	@Autowired
    private DataDifferenceService service;
	
	@MockBean
	private DataDifferenceEntryRepository dataRepository;
	
		/**
	 * Test method for the incomplete cycle request 
	 * (i.e. only one panel uploaded before the difference endpoint be called)
	 */
	
	@Test(expected = TransactionIncompleteException.class)
	public void incomplete_cycle_request() {

		/** ARRANGE */
    	String transactionId = String.valueOf(ThreadLocalRandom.current().nextInt(1, 1000));
		
    	String content = 
    			Base64.getEncoder().encodeToString(
						WaesScalableWebApplicationConstants.JSON_STRING_1.getBytes());
    	
    	when(dataRepository.findById(String.valueOf(transactionId)))
			.thenReturn(Optional.of(new DataDifferenceEntry(transactionId, content, null)));

		/** ACT */
    	service.difference(transactionId);

	}
	
	/**
	 * Complete cycle with both panels of equal content (No differences should be found).
	 */
	@Test
	public void complete_cycle_request_NO_DIFFERENCES() {
		try {
			
			/** ARRANGE */
	    	String transactionId = String.valueOf(ThreadLocalRandom.current().nextInt(1, 1000));
			
	    	String content = 
	    			Base64.getEncoder().encodeToString(
							WaesScalableWebApplicationConstants.JSON_STRING_1.getBytes());
	    	
	    	when(dataRepository.findById(String.valueOf(transactionId)))
    			.thenReturn(Optional.of(new DataDifferenceEntry(transactionId, content, content)));

			/** ACT */
	    	DataDifferenceResult dataDifferenceResult = service.difference(transactionId);
	    	
			
			/** ASSERT */
	        assertThat(dataDifferenceResult.getResult()).isEqualTo(JsonContentsResult.EQUAL_CONTENTS);
			
		} catch (Exception e) {
		}
	}
	
	/**
	 * Complete cycle with panels of different content (Differences should be found).
	 */
	@Test
	public void complete_cycle_request_WITH_DIFFERENCES() {
		try {
			
			/** ARRANGE */
	    	String transactionId = String.valueOf(ThreadLocalRandom.current().nextInt(1, 1000));
			
	    	String contentLeft = 
	    			Base64.getEncoder().encodeToString(
							WaesScalableWebApplicationConstants.JSON_STRING_2.getBytes());
	    	
	    	String contentRight = 
	    			Base64.getEncoder().encodeToString(
							WaesScalableWebApplicationConstants.JSON_STRING_3.getBytes());
	    	
	    	when(dataRepository.findById(String.valueOf(transactionId)))
    			.thenReturn(Optional.of(new DataDifferenceEntry(transactionId, contentLeft, contentRight)));

			/** ACT */
	    	DataDifferenceResult dataDifferenceResult = service.difference(transactionId);
	    	
			
			/** ASSERT */
	        assertThat(dataDifferenceResult.getResult()).isEqualTo(JsonContentsResult.DIFFERENT_SIZES_CONTENTS);
	        assertThat(dataDifferenceResult.getLeftDifferences().size()).isEqualTo(3);
	        assertThat(dataDifferenceResult.getRightDifferences().size()).isEqualTo(2);
			
		} catch (Exception e) {
		}
	}

	/**
	 * Straight to difference without upload any info. 
	 */
	@Test(expected = TransactionNotFoundException.class)
	public void transaction_not_found_request() {

		/** ARRANGE */
    	String transactionId = String.valueOf(ThreadLocalRandom.current().nextInt(1, 1000));
		
    	when(dataRepository.findById(String.valueOf(transactionId)))
			.thenThrow(new TransactionNotFoundException("No transaction found for the transactionId provided"));

		/** ACT */
    	service.difference(transactionId);
    	
	}
}
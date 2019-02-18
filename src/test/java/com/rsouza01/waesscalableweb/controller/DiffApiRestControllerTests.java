package com.rsouza01.waesscalableweb.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.rsouza01.waesscalableweb.WaesScalableWebApplicationConstants;
import com.rsouza01.waesscalableweb.enums.JsonContentsResult;
import com.rsouza01.waesscalableweb.enums.PanelSide;
import com.rsouza01.waesscalableweb.exception.InvalidJSONFormatException;
import com.rsouza01.waesscalableweb.exception.TransactionIncompleteException;
import com.rsouza01.waesscalableweb.exception.TransactionNotFoundException;
import com.rsouza01.waesscalableweb.model.DataDifferenceResult;
import com.rsouza01.waesscalableweb.service.DataDifferenceService;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@WebMvcTest(DataDifferenceApiRestController.class)
public class DiffApiRestControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DataDifferenceService service;

	private static final String DIFFERENCE_GET_ENDPOINT = "/v1/diff/{id}";
	private static final String CONTENT_POST_ENDPOINT 	= "/v1/diff/{id}/{panel}";

	/**
	 * Test method for a invalid JSON format 
	 */
	@Test
	public void invalid_JSON_content_request() {
		try {
			
			/** ARRANGE */
	    	int transactionId = ThreadLocalRandom.current().nextInt(1, 1000);

	    	String encodedString = 
	    			String.format(
	    					WaesScalableWebApplicationConstants.JSON_DIFF_REQUEST, 
	    					Base64.getEncoder().encodeToString(
	    							WaesScalableWebApplicationConstants.JSON_INVALID.getBytes()));
	    	
	    	PanelSide side = PanelSide.left;
	    	
	    	when(service.inputData(String.valueOf(transactionId), side, encodedString))
    		.thenThrow(new InvalidJSONFormatException("The content is not in a valid JSON format."));

	    	/** ACT & ASSERT */
			content_post_request(transactionId, 
					encodedString,
					side)
				.andExpect(status().isCreated());
			
		} catch (Exception e) {
		}
	}
	
	/**
	 * Test method for the incomplete cycle request 
	 * (i.e. only one panel uploaded before the difference endpoint be called)
	 */
	@Test
	public void incomplete_cycle_request() {
		
		try {

			/** ARRANGE */
	    	int transactionId = ThreadLocalRandom.current().nextInt(1, 1000);

	    	when(service.difference(String.valueOf(transactionId)))
	    		.thenThrow(new TransactionIncompleteException("Two panels are needed (1 found)."));

	    	String encodedString = 
	    			String.format(
	    					WaesScalableWebApplicationConstants.JSON_DIFF_REQUEST, 
	    					Base64.getEncoder().encodeToString(
	    							WaesScalableWebApplicationConstants.JSON_STRING_1.getBytes()));

			/** ACT & ASSERT */
	    	content_post_request(transactionId, 
					encodedString,
					PanelSide.left)
				.andExpect(status().isCreated());
		
			difference_get_request(transactionId)
				//.andDo(print())
				.andExpect(status().isUnprocessableEntity())
				.andExpect(jsonPath("message").value("Two panels are needed (1 found)."));

		} catch (Exception e) {
		}
	}
	
	/**
	 * Complete cycle with both panels of equal content (No differences should be found).
	 */
	@Test
	public void complete_cycle_request_NO_DIFFERENCES() {
		
		try {
			
			/** ARRANGE */
	    	int transactionId = ThreadLocalRandom.current().nextInt(1, 1000);
			
	    	when(service.difference(String.valueOf(transactionId)))
    		.thenReturn(
    				new DataDifferenceResult(
						String.valueOf(transactionId),
						JsonContentsResult.EQUAL_CONTENTS,null, null)
    				);

	    	String encodedString = 
	    			String.format(
	    					WaesScalableWebApplicationConstants.JSON_DIFF_REQUEST, 
	    					Base64.getEncoder().encodeToString(
	    							WaesScalableWebApplicationConstants.JSON_STRING_1.getBytes()));
	    	
			/** ACT & ASSERT */
			content_post_request(transactionId, 
					encodedString,
					PanelSide.left)
				.andExpect(status().isCreated());
		
			content_post_request(transactionId, 
					encodedString,
					PanelSide.right)
				.andExpect(status().isCreated());
			
			difference_get_request(transactionId)
				//.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("result").value("EQUAL_CONTENTS"));
			
			
		} catch (Exception e) {
		}
	}
	
	/**
	 * Complete cycle with panels of different content (Differences should be found).
	 */
	@Test
	public void complete_cycle_request_WITH_DIFFERENCES() {
		
		try {
	    	int transactionId = ThreadLocalRandom.current().nextInt(1, 1000);

	    	when(service.difference(String.valueOf(transactionId)))
    		.thenReturn(
    				new DataDifferenceResult(
						String.valueOf(transactionId),
						JsonContentsResult.DIFFERENT_SIZES_CONTENTS, null, null)
    				);

	    	
	    	String encodedStringLeft = 
	    			String.format(
	    					WaesScalableWebApplicationConstants.JSON_DIFF_REQUEST, 
	    					Base64.getEncoder().encodeToString(
	    							WaesScalableWebApplicationConstants.JSON_STRING_2.getBytes()));

	    	String encodedStringRight = 
	    			String.format(
	    					WaesScalableWebApplicationConstants.JSON_DIFF_REQUEST, 
	    					Base64.getEncoder().encodeToString(
	    							WaesScalableWebApplicationConstants.JSON_STRING_3.getBytes()));
	    	

	    	content_post_request(transactionId, 
	    			encodedStringLeft,
	    			PanelSide.left)
				.andExpect(status().isCreated());
			
			content_post_request(transactionId, 
					encodedStringRight,
					PanelSide.right)
				.andExpect(status().isCreated());

			difference_get_request(transactionId)
				//.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("result").value("DIFFERENT_SIZES_CONTENTS"));
			
			
		} catch (Exception e) {
		}
		
	}

	/**
	 * Straight to difference without upload any info. 
	 */
	@Test
	public void transaction_not_found_request() {
		
		try {

	    	int transactionId = ThreadLocalRandom.current().nextInt(1, 1000);

	    	when(service.difference(String.valueOf(transactionId)))
	    		.thenThrow(new TransactionNotFoundException("No transaction found for the transactionId provided"));
	    	
			difference_get_request(transactionId)
				//.andDo(print())
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("message").value("No transaction found for the transactionId provided"));

		} catch (Exception e) {
		}
	}

	/**
	 * Auxiliary method to retrieve the ResultActions associated to the difference GET request
	 *  
	 * @param transactionId the transaction id.
	 * @return ResultActions from the request
	 * @throws Exception
	 */
	public ResultActions difference_get_request(int transactionId) throws Exception {
		
        return mockMvc.perform(get(
        		DiffApiRestControllerTests.DIFFERENCE_GET_ENDPOINT, 
        		String.valueOf(transactionId))
        		.contentType(MediaType.APPLICATION_JSON));
        
	}

	/**
	 * Auxiliary method to retrieve the ResultActions associated to the difference GET request
	 * 
	 * @param transactionId the transaction id.
	 * @param content the JSON base 64 content
	 * @param panelSide the panel side (left or right)
	 * @return ResultActions from the request
	 * @throws Exception
	 */
	public ResultActions content_post_request(int transactionId, String content, PanelSide panelSide) throws Exception {

        return mockMvc.perform(
        		post(DiffApiRestControllerTests.CONTENT_POST_ENDPOINT, 
        				String.valueOf(transactionId), 
        				panelSide.name())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content));
	}
}

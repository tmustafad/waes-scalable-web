package com.rsouza01.waesscalableweb.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.rsouza01.waesscalableweb.enums.PanelSide;
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

	private static final String JSON_DIFF_REQUEST 		= "{\"base64Content\":\"DIFF REQUEST\"}";
	private static final String JSON_DIFF_REQUEST_LEFT 	= "{\"base64Content\":\"DIFF REQUEST LEFT\"}";
	private static final String JSON_DIFF_REQUEST_RIGHT = "{\"base64Content\":\"DIFF REQUEST RIGHT\"}";

	/**
	 * Test method for the incomplete cycle request 
	 * (i.e. only one panel uploaded before the difference endpoint be called)
	 */
	@Test
	public void incomplete_cycle_request() {
		
		try {

	    	int transactionId = ThreadLocalRandom.current().nextInt(1, 1000);

	    	when(service.difference(String.valueOf(transactionId)))
	    		.thenThrow(new TransactionNotFoundException("Two panels are needed (1 found)."));
	    	
			content_post_request(transactionId, JSON_DIFF_REQUEST, PanelSide.left)
				.andExpect(status().isCreated());
		
			difference_get_request(transactionId)
				//.andDo(print())
				.andExpect(status().isNotFound())
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
			
	    	int transactionId = ThreadLocalRandom.current().nextInt(1, 1000);
			
	    	when(service.difference(String.valueOf(transactionId)))
    		.thenReturn(null);

			content_post_request(transactionId, JSON_DIFF_REQUEST, PanelSide.left)
				.andExpect(status().isCreated());
		
			content_post_request(transactionId, JSON_DIFF_REQUEST, PanelSide.right)
				.andExpect(status().isCreated());
			
			difference_get_request(transactionId)
				//.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("status").value("OK"))
				.andExpect(jsonPath("message").value("No Differences found"));
			
			
		} catch (Exception e) {
			// TODO: handle exception
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
    		.thenReturn(new DataDifferenceResult());

	    	content_post_request(transactionId, JSON_DIFF_REQUEST_LEFT, PanelSide.left)
				.andExpect(status().isCreated());
			
			content_post_request(transactionId, JSON_DIFF_REQUEST_RIGHT, PanelSide.right)
				.andExpect(status().isCreated());

			difference_get_request(transactionId)
				//.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("status").value("OK"))
				.andExpect(jsonPath("message").value("Differences found"));
			
			
		} catch (Exception e) {
			// TODO: handle exception
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
		
        return mockMvc.perform(get(DiffApiRestControllerTests.DIFFERENCE_GET_ENDPOINT, String.valueOf(transactionId))
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

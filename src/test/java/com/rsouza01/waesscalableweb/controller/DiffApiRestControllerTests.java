package com.rsouza01.waesscalableweb.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

import com.rsouza01.waesscalableweb.service.DataDifferenceService;

@RunWith(SpringRunner.class)
@WebMvcTest(DataDifferenceApiRestController.class)
public class DiffApiRestControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DataDifferenceService service;

	private static final String JSON_DIFF_REQUEST 		= "{\"content\":\"DIFF REQUEST\"}";
	private static final String JSON_DIFF_REQUEST_LEFT 	= "{\"content\":\"DIFF REQUEST LEFT\"}";
	private static final String JSON_DIFF_REQUEST_RIGHT = "{\"content\":\"DIFF REQUEST RIGHT\"}";

	@Test
	public void complete_cycle_request_NO_DIFFERENCES() {
		
		try {
	    	int transactionId = ThreadLocalRandom.current().nextInt(1, 1000);
			
			left_post_request(transactionId, JSON_DIFF_REQUEST);
			right_post_request(transactionId, JSON_DIFF_REQUEST);
			difference_get_request(transactionId);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	@Test
	public void complete_cycle_request_WITH_DIFFERENCES() {
		
		try {
	    	int transactionId = ThreadLocalRandom.current().nextInt(1, 1000);
			
			left_post_request(transactionId, JSON_DIFF_REQUEST_LEFT);
			right_post_request(transactionId, JSON_DIFF_REQUEST_RIGHT);
			difference_get_request(transactionId);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void left_post_request(int transactionId, String content) throws Exception {

        mockMvc.perform(post("/v1/diff/{id}/left", String.valueOf(transactionId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
				//.andDo(print())
				.andExpect(status().isOk());
        
	}

	public void right_post_request(int transactionId, String content) throws Exception {

        mockMvc.perform(post("/v1/diff/{id}/right", String.valueOf(transactionId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
				//.andDo(print())
				.andExpect(status().isOk());
        
	}
	
	public void difference_get_request(int transactionId) throws Exception {
		
        mockMvc.perform(get("/v1/diff/{id}", String.valueOf(transactionId))
        		.contentType(MediaType.APPLICATION_JSON))
				//.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("status").value("OK"))
				.andExpect(jsonPath("message").value(""));
        
	}
}

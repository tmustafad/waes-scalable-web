package com.rsouza01.waesscalableweb.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Method;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.rsouza01.waesscalableweb.enums.PanelSide;
import com.rsouza01.waesscalableweb.service.DataDifferenceService;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

@RunWith(SpringRunner.class)
@WebMvcTest(DataDifferenceApiRestController.class)
public class DiffApiRestControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DataDifferenceService service;

	private static final String DIFFERENCE_GET_ENDPOINT = "/v1/diff/{id}";
	private static final String CONTENT_POST_ENDPOINT 	= "/v1/diff/{id}/{panel}";

	private static final String JSON_DIFF_REQUEST 		= "{\"content\":\"DIFF REQUEST\"}";
	private static final String JSON_DIFF_REQUEST_LEFT 	= "{\"content\":\"DIFF REQUEST LEFT\"}";
	private static final String JSON_DIFF_REQUEST_RIGHT = "{\"content\":\"DIFF REQUEST RIGHT\"}";

	@Test
	public void incomplete_cycle_request() {
		
		try {
			
	    	int transactionId = ThreadLocalRandom.current().nextInt(1, 1000);
			
			content_post_request(transactionId, JSON_DIFF_REQUEST, PanelSide.LEFT)
				.andExpect(status().isOk());
		
			difference_get_request(transactionId)
				//.andDo(print())
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("message").value("Two panels are needed (1 found)."));

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void complete_cycle_request_NO_DIFFERENCES() {
		
		try {
			
	    	int transactionId = ThreadLocalRandom.current().nextInt(1, 1000);
			
			content_post_request(transactionId, JSON_DIFF_REQUEST, PanelSide.LEFT)
				.andExpect(status().isOk());
		
			content_post_request(transactionId, JSON_DIFF_REQUEST, PanelSide.RIGHT)
				.andExpect(status().isOk());
			
			difference_get_request(transactionId)
				//.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("status").value("OK"))
				.andExpect(jsonPath("message").value(""));
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void complete_cycle_request_WITH_DIFFERENCES() {
		
		try {
	    	int transactionId = ThreadLocalRandom.current().nextInt(1, 1000);
			
			content_post_request(transactionId, JSON_DIFF_REQUEST_LEFT, PanelSide.LEFT)
				.andExpect(status().isOk());
			
			content_post_request(transactionId, JSON_DIFF_REQUEST_RIGHT, PanelSide.RIGHT)
				.andExpect(status().isOk());

			difference_get_request(transactionId)
				//.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("status").value("OK"))
				.andExpect(jsonPath("message").value(""));
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public ResultActions difference_get_request(int transactionId) throws Exception {
		
        return mockMvc.perform(get(DiffApiRestControllerTests.DIFFERENCE_GET_ENDPOINT, String.valueOf(transactionId))
        		.contentType(MediaType.APPLICATION_JSON));
        
	}

	public ResultActions content_post_request(int transactionId, String content, PanelSide panelSide) throws Exception {

        return mockMvc.perform(
        		post(DiffApiRestControllerTests.CONTENT_POST_ENDPOINT, 
        				String.valueOf(transactionId), 
        				panelSide.getPanelSide())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content));
	}
}

package com.rsouza01.waesscalableweb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.runners.MethodSorters;

import com.rsouza01.waesscalableweb.service.DataDiffService;

@RunWith(SpringRunner.class)
@WebMvcTest(DiffApiRestController.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DiffApiRestControllerTests {

	private Logger logger = LoggerFactory.getLogger(DiffApiRestControllerTests.class);

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DataDiffService service;

	private static final String JSON_DIFF_REQUEST = "{\"content\":\"DIFF REQUEST\"}";
	
	private int transactionId = 0;   
	
    @Before
    public void setup() {
    	transactionId = ThreadLocalRandom.current().nextInt(1, 1000);

    }
    
	@Test
	public void a_left_post_request() throws Exception {

        mockMvc.perform(post("/v1/diff/{id}/left", String.valueOf(transactionId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(DiffApiRestControllerTests.JSON_DIFF_REQUEST))
				.andDo(print())
				.andExpect(status().isOk());
        
	}

	@Test
	public void b_right_post_request() throws Exception {

        mockMvc.perform(post("/v1/diff/{id}/right", String.valueOf(transactionId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(DiffApiRestControllerTests.JSON_DIFF_REQUEST))
				.andDo(print())
				.andExpect(status().isOk());
        
	}
	
	@Test
	public void c_difference_get_request() throws Exception {

        mockMvc.perform(get("/v1/diff/{id}", String.valueOf(transactionId))
        		.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
        
	}
}

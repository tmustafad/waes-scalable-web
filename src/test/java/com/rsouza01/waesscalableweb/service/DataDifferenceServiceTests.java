package com.rsouza01.waesscalableweb.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.rsouza01.waesscalableweb.service.DataDifferenceService;

@RunWith(SpringRunner.class)
@WebMvcTest(DataDifferenceService.class)
public class DataDifferenceServiceTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DataDifferenceService service;

	/**
	 * Test method for the incomplete cycle request 
	 * (i.e. only one panel uploaded before the difference endpoint be called)
	 */
	@Test
	public void incomplete_cycle_request() {
		
	}
	
	/**
	 * Complete cycle with both panels of equal content (No differences should be found).
	 */
	@Test
	public void complete_cycle_request_NO_DIFFERENCES() {
		
	}
	
	/**
	 * Complete cycle with panels of different content (Differences should be found).
	 */
	@Test
	public void complete_cycle_request_WITH_DIFFERENCES() {
		
	}

	/**
	 * Straight to difference without upload any info. 
	 */
	@Test
	public void transaction_not_found_request() {
	}
}
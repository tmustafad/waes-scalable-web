package com.rsouza01.waesscalableweb.util.json;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rsouza01.waesscalableweb.WaesScalableWebApplicationConstants;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class JsonObjectTests {
	
	private Logger logger = LoggerFactory.getLogger(JsonObjectTests.class);

	@Test
	public void test_JsonObject_equal_contents_json() {

		try {
			
			/** ARRANGE */
			JsonObject leftJsonObject = 
					new JsonObject(
							WaesScalableWebApplicationConstants.JSON_STRING_2);

			JsonObject rightJsonObject = 
					new JsonObject(
							WaesScalableWebApplicationConstants.JSON_STRING_2);
			
			/** ACT */
			List<JsonObjectDifference> leftDifferences = 
					leftJsonObject.getDifferencesWith(rightJsonObject);

			/** ASSERT */
			assertThat(leftDifferences.size()).isEqualTo(0);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Test
	public void test_JsonObject_equal_different_json() {

		try {
			
			/** ARRANGE */
			JsonObject leftJsonObject = 
					new JsonObject(
							WaesScalableWebApplicationConstants.JSON_STRING_2);

			JsonObject rightJsonObject = 
					new JsonObject(
							WaesScalableWebApplicationConstants.JSON_STRING_3);
			
			/** ACT */
			List<JsonObjectDifference> leftDifferences = 
					leftJsonObject.getDifferencesWith(rightJsonObject);

			/** ASSERT */
			assertThat(leftDifferences.size()).isEqualTo(3);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}

package com.rsouza01.waesscalableweb.util.json;

import org.junit.Test;

import com.rsouza01.waesscalableweb.WaesScalableWebApplicationConstants;
import com.rsouza01.waesscalableweb.enums.JsonContentsResult;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonContentsComparatorTests {

	@Test
	public void test_JsonContentsComparator_equal_contents_json() {

		/** ARRANGE */
		JsonContentsComparator jsonContentsComparator = 
				new JsonContentsComparator(
						WaesScalableWebApplicationConstants.JSON_STRING_1, 
						WaesScalableWebApplicationConstants.JSON_STRING_1);
		
		/** ACT */
		JsonContentsComparison jsonContentsComparison = 
				jsonContentsComparator.compare();
		
		/** ASSERT */
		assertThat(jsonContentsComparison.getResult()).isEqualTo(JsonContentsResult.EQUAL_CONTENTS);
		assertThat(jsonContentsComparison.hasDifferences()).isEqualTo(false);
		assertThat(
				(jsonContentsComparison.getRightDifferences().size() + jsonContentsComparison.getLeftDifferences().size())
				).isEqualTo(0);

	}

	//@Test
	public void test_JsonContentsComparator_equal_size_different_contents_json() {

		/** ARRANGE */
		JsonContentsComparator jsonContentsComparator = 
				new JsonContentsComparator(
						WaesScalableWebApplicationConstants.JSON_STRING_2, 
						WaesScalableWebApplicationConstants.JSON_STRING_3);
		
		/** ACT */
		JsonContentsComparison jsonContentsComparison = jsonContentsComparator.compare();
		
		/** ASSERT */
		assertThat(jsonContentsComparison.getResult()).isEqualTo(JsonContentsResult.SAME_SIZES_BUT_DIFFERENT_CONTENTS);

	}
}

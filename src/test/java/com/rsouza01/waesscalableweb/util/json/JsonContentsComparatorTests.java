package com.rsouza01.waesscalableweb.util.json;

import org.junit.Test;

import com.rsouza01.waesscalableweb.enums.JsonContentsResult;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonContentsComparatorTests {

	private static final String JSON_DIFF_REQUEST 		= "{\"base64Content\":\"DIFF REQUEST\"}";
	private static final String JSON_DIFF_REQUEST_LEFT 	= "{\"base64Content\":\"DIFF REQUEST  LEFT\"}";
	private static final String JSON_DIFF_REQUEST_RIGHT = "{\"base64Content\":\"DIFF REQUEST RIGHT\"}";

	@Test
	public void test_JsonContentsComparator_equal_contents_json() {

		/** ARRANGE */
		JsonContentsComparator jsonContentsComparator = 
				new JsonContentsComparator(
						JsonContentsComparatorTests.JSON_DIFF_REQUEST, 
						JsonContentsComparatorTests.JSON_DIFF_REQUEST);
		
		/** ACT */
		JsonContentsComparison jsonContentsComparison = jsonContentsComparator.compare();
		
		/** ASSERT */
		assertThat(jsonContentsComparison.getResult()).isEqualTo(JsonContentsResult.EQUAL_CONTENTS);
		assertThat(jsonContentsComparison.hasDifferences()).isEqualTo(false);
		assertThat(jsonContentsComparison.getDifferences().size()).isEqualTo(0);

	}

	@Test
	public void test_JsonContentsComparator_equal_size_different_contents_json() {

		/** ARRANGE */
		JsonContentsComparator jsonContentsComparator = 
				new JsonContentsComparator(
						JsonContentsComparatorTests.JSON_DIFF_REQUEST_LEFT, 
						JsonContentsComparatorTests.JSON_DIFF_REQUEST_RIGHT);
		
		/** ACT */
		JsonContentsComparison jsonContentsComparison = jsonContentsComparator.compare();
		
		/** ASSERT */
		assertThat(jsonContentsComparison.getResult()).isEqualTo(JsonContentsResult.SAME_SIZES_BUT_DIFFERENT_CONTENTS);

	}
}

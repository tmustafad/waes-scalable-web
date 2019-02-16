/**
 * 
 */
package com.rsouza01.waesscalableweb.util.json;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

import com.rsouza01.waesscalableweb.enums.JsonContentsResult;
/**
 * @author rsouza
 *
 */
@Getter
public class JsonContentsComparison {
	
	public JsonContentsComparison(JsonContentsResult result) {
		this.result = result; 
	}
	
	private JsonContentsResult result;
	
	private List<String> differences = new ArrayList<>();

	public boolean hasDifferences() {
		return differences.size() > 0;
	}
}

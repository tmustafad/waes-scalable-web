package com.rsouza01.waesscalableweb.model;

import java.io.Serializable;

import com.rsouza01.waesscalableweb.util.json.JsonContentsComparator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DataDifferenceResult implements Serializable {

	/**
	 * @param jsonContentsComparator
	 */
	public DataDifferenceResult(JsonContentsComparator jsonContentsComparator) {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7535955528281487172L;

	private String id;
	
	private String[] differences;
}

package com.rsouza01.waesscalableweb.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.rsouza01.waesscalableweb.enums.PanelSide;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
public class DataDifferenceEntry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7535955528281487172L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id = "";

	private String transactionId;
	
	private PanelSide panelSide;

	private String content;

	/**
	 * @param transactionId
	 * @param panelSide
	 * @param content
	 */
	public DataDifferenceEntry(String transactionId, PanelSide panelSide, String content) {
		super();
		this.transactionId = transactionId;
		this.panelSide = panelSide;
		this.content = content;
	}
}

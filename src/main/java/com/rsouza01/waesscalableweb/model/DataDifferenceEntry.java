package com.rsouza01.waesscalableweb.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.rsouza01.waesscalableweb.enums.PanelSide;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
@Table(name = "data_difference_entry")
public class DataDifferenceEntry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7535955528281487172L;

	@Id 
	@NotNull
	@Size(min=1, message="A transaction ID must be provided")
	private String transactionId;
	
	private String leftContent;

	private String rightContent;

	public DataDifferenceEntry(String transactionId, String leftContent, String rightContent) {
		super();
		
		this.transactionId = transactionId;
		this.leftContent = leftContent;
		this.rightContent = rightContent;
	}

	public DataDifferenceEntry(String transactionId, PanelSide panelSide, String content) {
		super();

		this.transactionId = transactionId;
		
		if(panelSide == PanelSide.left) this.leftContent = content;
		else this.rightContent = content;
	}
}
package com.rsouza01.waesscalableweb.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.rsouza01.waesscalableweb.enums.PanelSide;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity with both the panels' contents.
 * 
 * @author Rodrigo Souza (rsouza01)
 *
 */
@Entity @Getter @Setter @NoArgsConstructor
@Table(name = "data_difference_entry")
public class DataDifferenceEntry implements Serializable {

	/** Serial Version UID */
	private static final long serialVersionUID = 7535955528281487172L;

	/**
	 * Transaction ID for the operation.
	 */
	@Id 
	@NotNull
	@Size(min=1, message="A transaction ID must be provided")
	private String transactionId;
	
	/**
	 * Left panel contents encoded in base-64 format.
	 */
	private String leftContent;

	/**
	 * Right panel contents encoded in base-64 format.
	 */
	private String rightContent;


	/**
	 * Constructor
	 * 
	 * @param transactionId The transaction ID for the operation
	 */
	public DataDifferenceEntry(String transactionId) {
		super();
		this.transactionId = transactionId;
	}

	/**
	 * 
	 * @param transactionId The transaction ID for the operation
	 * @param leftContent Left panel contents encoded in base-64 format.
	 * @param rightContent Right panel contents encoded in base-64 format.
	 */
	public DataDifferenceEntry(String transactionId, String leftContent, String rightContent) {
		super();
		
		this.transactionId = transactionId;
		this.leftContent = leftContent;
		this.rightContent = rightContent;
	}

	/**
	 * 
	 * @param transactionId The transaction ID for the operation
	 * @param panelSide The Panel side.
	 * @param content The panel content
	 */
	public DataDifferenceEntry(String transactionId, PanelSide panelSide, String content) {
		super();

		this.transactionId = transactionId;
		
		if(panelSide == PanelSide.left) this.leftContent = content;
		else this.rightContent = content;
	}
}
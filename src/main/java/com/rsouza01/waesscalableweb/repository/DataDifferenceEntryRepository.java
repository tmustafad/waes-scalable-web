package com.rsouza01.waesscalableweb.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rsouza01.waesscalableweb.model.DataDifferenceEntry;


/**
 * The repository interface for database related operations for the {@code DataDifferenceEntry} class. 
 * @author Rodrigo Souza (rsouza01)
 *
 */
public interface DataDifferenceEntryRepository extends CrudRepository<DataDifferenceEntry, String> {


	/**
	 * Method responsible for finding the records with a given transactionId. 
	 * @param transactionId the transaction Id for the operation.
	 * @return a list with {@code DataDifferenceEntry} matching the query 
	 */
	List<DataDifferenceEntry> findByTransactionId(String transactionId);
	
}

package com.rsouza01.waesscalableweb.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rsouza01.waesscalableweb.model.DataDifferenceEntry;


public interface DataDifferenceEntryRepository extends CrudRepository<DataDifferenceEntry, String> {

	/**
	 * @param transactionId
	 * @return
	 */
	List<DataDifferenceEntry> findByTransactionId(String transactionId);
	
}

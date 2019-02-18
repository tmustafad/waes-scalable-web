package com.rsouza01.waesscalableweb.repository;

import org.springframework.data.repository.CrudRepository;

import com.rsouza01.waesscalableweb.model.DataDifferenceEntry;


/**
 * The repository interface for database related operations for the {@code DataDifferenceEntry} class. 
 * @author Rodrigo Souza (rsouza01)
 *
 */
public interface DataDifferenceEntryRepository extends CrudRepository<DataDifferenceEntry, String> {

}

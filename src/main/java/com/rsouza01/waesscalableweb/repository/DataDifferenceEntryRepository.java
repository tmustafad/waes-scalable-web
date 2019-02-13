package com.rsouza01.waesscalableweb.repository;

import org.springframework.data.repository.CrudRepository;

import com.rsouza01.waesscalableweb.model.DataDifferenceEntry;


public interface DataDifferenceEntryRepository extends CrudRepository<DataDifferenceEntry, String> {
	
}

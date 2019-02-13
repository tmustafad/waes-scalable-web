package com.rsouza01.waesscalableweb.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rsouza01.waesscalableweb.controller.DiffApiRestController;
import com.rsouza01.waesscalableweb.enums.PanelSide;
import com.rsouza01.waesscalableweb.model.DataDiffEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DataDiffServiceImpl implements DataDiffService {

	private Logger logger = LoggerFactory.getLogger(DiffApiRestController.class);

	@Override
	public void inputData(String id, String input, PanelSide panelSide) {

		final String logMessage = "Transaction %s: InputData: %s";
		
		logger.debug(String.format(logMessage, id, input));

	}

	@Override
	public DataDiffEntry difference(String id) {
		
		final String logMessage = "Transaction %s: Difference requested.";
		
		logger.debug(String.format(logMessage, id));

		return null;
	}
}

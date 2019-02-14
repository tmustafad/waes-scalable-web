package com.rsouza01.waesscalableweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rsouza01.waesscalableweb.dto.DifferenceRequest;
import com.rsouza01.waesscalableweb.dto.DifferenceResponse;
import com.rsouza01.waesscalableweb.enums.PanelSide;
import com.rsouza01.waesscalableweb.exception.TransactionNotFoundException;
import com.rsouza01.waesscalableweb.model.DataDifferenceResult;
import com.rsouza01.waesscalableweb.service.DataDifferenceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Api(value = "Computes differences between base-64 data")
@RestController
@RequestMapping("v1/diff")
public class DataDifferenceApiRestController {

	private Logger logger = LoggerFactory.getLogger(DataDifferenceApiRestController.class);

	@Autowired
	private DataDifferenceService service;

	@ApiOperation(value = "Endpoint that uploads a content for the left panel")
	@RequestMapping(value = "{transactionId}/left", method = RequestMethod.POST)
	public ResponseEntity<String> leftUpload(@PathVariable final String transactionId,
			@Valid @RequestBody final DifferenceRequest diffRequest) {

		final String logMessage = "Transaction %s: Left panel upload: %s";

		try {

			logger.debug(String.format(logMessage, transactionId, diffRequest.getContent()));

			service.inputData(transactionId, PanelSide.LEFT, diffRequest.getContent());

			return new ResponseEntity<String>("OK", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Endpoint that uploads a content for the right panel")
	@RequestMapping(value = "{transactionId}/right", method = RequestMethod.POST)
	public ResponseEntity<String> rightUpload(@PathVariable final String transactionId,
			@Valid @RequestBody final DifferenceRequest diffRequest) {

		final String logMessage = "Transaction %s: Right panel upload: %s";

		try {

			logger.debug(String.format(logMessage, transactionId, diffRequest.getContent()));

			service.inputData(transactionId, PanelSide.RIGHT, diffRequest.getContent());

			return new ResponseEntity<String>("OK", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Endpoint that gets the differences between two panels identified by an Id")
	@RequestMapping(value = "{transactionId}", method = RequestMethod.GET)
	public ResponseEntity<DifferenceResponse> difference(@PathVariable final String transactionId) {

		final String logMessage = "Transaction %s: Difference requested.";

		logger.debug(String.format(logMessage, transactionId));

		DataDifferenceResult dataDifferenceResult = service.difference(transactionId);
		
		DifferenceResponse differenceResponse = new DifferenceResponse("NOK", "", null);
		
		if(dataDifferenceResult != null) {
			differenceResponse = new DifferenceResponse("OK", "Differences found", dataDifferenceResult.getDifferences());
		}

		return new ResponseEntity<DifferenceResponse>(differenceResponse, HttpStatus.OK);

	}
}

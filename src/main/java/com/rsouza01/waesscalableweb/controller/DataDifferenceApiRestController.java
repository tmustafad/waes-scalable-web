package com.rsouza01.waesscalableweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rsouza01.waesscalableweb.dto.DifferenceRequest;
import com.rsouza01.waesscalableweb.dto.DifferenceResponse;
import com.rsouza01.waesscalableweb.enums.PanelSide;
import com.rsouza01.waesscalableweb.model.DataDifferenceResult;
import com.rsouza01.waesscalableweb.service.DataDifferenceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The main controller. It performs difference operations between
 * two JSON-base64 formatted panels.
 * 
 * @author Rodrigo Souza (rsouza01)
 *
 */
@Api(value = "Computes differences between base-64 data")
@RestController
@RequestMapping("v1/diff")
public class DataDifferenceApiRestController {

	/** Class logger */
	private Logger logger = LoggerFactory.getLogger(DataDifferenceApiRestController.class);

	@Autowired
	/** Bean for data difference service*/
	private DataDifferenceService service;

	/** Log message for content upload */
	public static final String contentUploadLogMessage = "Transaction %s: %s panel upload: %s";
	
	/** Log message for content upload error */
	public static final String contentUploadErrorlogMessage = "Error for Transaction %s: %s panel upload: %s";

	/** Log message for difference request */
	public static final String differencelogMessage = "Transaction %s: Difference requested.";
	

	/**
	 * Endpoint that uploads content for a specified side (left/right)
	 * 
	 * @param transactionId the transaction id for the operation
	 * @param panelSide the panel side for the operation (left or right)
	 * @param diffRequest the panel contents
	 * @return HTTP Status 201 for a successful request or HTTP Status 400 for a invalid request.
	 */
	@ApiOperation(value = "Endpoint that uploads content for a specified side.")
	@RequestMapping(value = "{transactionId}/{panelSide}", method = RequestMethod.POST)
	public ResponseEntity<String> contentUpload(
			@PathVariable(value = "transactionId") final String transactionId,
			@PathVariable(value = "panelSide") final PanelSide panelSide,
			@Valid @RequestBody final DifferenceRequest diffRequest) {

		logger.info(String.format(contentUploadLogMessage, transactionId, panelSide, diffRequest.getBase64Content()));
		
		service.inputData(transactionId, panelSide, diffRequest.getBase64Content());
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Endpoint that gets the differences between two panels identified by a transaction id
	 * 
	 * @param transactionId the transaction id for the operation
	 * @return HTTP Status 200 for a successful request
	 * 		   HTTP Status 422 if there is only one panel loaded or
	 * 		   HTTP Status 404 if there is no panel loaded
	 * .
	 */
	@ApiOperation(value = "Endpoint that gets the differences between two panels identified by a transaction id")
	@RequestMapping(value = "{transactionId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<DifferenceResponse> difference(@PathVariable final String transactionId) {
		
		logger.info(String.format(differencelogMessage, transactionId));

		DataDifferenceResult dataDifferenceResult = service.difference(transactionId);
		
		DifferenceResponse differenceResponse = new DifferenceResponse(dataDifferenceResult);
		
		return new ResponseEntity<DifferenceResponse>(differenceResponse, HttpStatus.OK);
	}
}
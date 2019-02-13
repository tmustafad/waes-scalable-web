package com.rsouza01.waesscalableweb.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rsouza01.waesscalableweb.dto.DiffRequest;
import com.rsouza01.waesscalableweb.enums.PanelSide;
import com.rsouza01.waesscalableweb.service.DataDiffService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Api(value = "Computes differences between base-64 data")
@RestController
@RequestMapping("v1/diff")
public class DiffApiRestController {
	
	private Logger logger = LoggerFactory.getLogger(DiffApiRestController.class);

	@Autowired
	private DataDiffService service;
	
	@ApiOperation(value = "Endpoint that uploads a content for the left panel")
    @RequestMapping(value="{id}/left", method = RequestMethod.POST)
    public ResponseEntity<String> leftUpload(@PathVariable final String id, @RequestBody final DiffRequest diffRequest) {

		final String logMessage = "Transaction %s: Left panel upload: %s";
		
		try {

	        logger.debug(String.format(logMessage, id, diffRequest.getContent()));

			service.inputData(id, diffRequest.getContent(), PanelSide.LEFT);
			
			return new ResponseEntity<String>("OK", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

	@ApiOperation(value = "Endpoint that uploads a content for the right panel")
    @RequestMapping(value="{id}/right", method = RequestMethod.POST)
    public ResponseEntity<String> rightUpload(@PathVariable final String id, @RequestBody final DiffRequest diffRequest) {

		final String logMessage = "Transaction %s: Right panel upload: %s";
		
		try {

	        logger.debug(String.format(logMessage, id, diffRequest.getContent()));

			service.inputData(id, diffRequest.getContent(), PanelSide.RIGHT);

	        return new ResponseEntity<String>("OK", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

	@ApiOperation(value = "Endpoint that gets the differences between two panels identified by an Id")
    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public ResponseEntity<String> difference(@PathVariable final String id) {

		final String logMessage = "Transaction %s: Difference requested.";

		try {

	        logger.debug(String.format(logMessage, id));

	        return new ResponseEntity<String>("OK", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}

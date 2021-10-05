package com.componentprocessing.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.componentprocessing.microservice.client.AuthClient;
import com.componentprocessing.microservice.dto.PaymentStatusDTO;
import com.componentprocessing.microservice.exceptions.ComponentTyepNotFoundException;
import com.componentprocessing.microservice.exceptions.InvalidTokenException;
import com.componentprocessing.microservice.model.ProcessRequest;
import com.componentprocessing.microservice.model.ProcessResponse;
import com.componentprocessing.microservice.repository.ProcessRequestRepo;
import com.componentprocessing.microservice.repository.ProcessResponseRepo;
import com.componentprocessing.microservice.service.ProcessResponseService;
import com.componentprocessing.microservice.service.RepairServiceImpl;
import com.componentprocessing.microservice.service.ReplacementServiceImpl;

import feign.FeignException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/componentprocessingmodule")
@Api(value = "Component Processing Resource endpoint")
public class ComponentProcessingController {

	@Autowired
	private RepairServiceImpl repairServiceImplObj;
	@Autowired
	private ReplacementServiceImpl replacementServiceImplObj;
	@Autowired
	private ProcessResponse processResponseObj;
	@Autowired
	private AuthClient authClient;
	@Autowired
	private ProcessRequestRepo processRequestRepository;
	@Autowired
	private ProcessResponseService service;
	@Autowired
	private ProcessResponseRepo processResponseRepository;

	/*
	 * POST: /CancelRequest (Input: RequestId, CreditCardNumber | Output: string
	 * response of the success of operation)
	 * http://localhost:8080/component/componentprocessingmodule/cancellrequest/R001
	 * /1234
	 */

	@PostMapping(path = "/cancellrequest/{id}/{creditCardNumber}")
	@ApiOperation(value = "to cancel the request", response = ProcessResponse.class, httpMethod = "POST")
	public String cancellRequest(@PathVariable("id") String id,
			@PathVariable("creditCardNumber") String creditCardNumber,
			@RequestHeader(name = "Authorization", required = true) String token) throws InvalidTokenException {

		log.info(token);
		try {
			if (!authClient.validatingAuthorizationToken(token).isValidStatus()) {

				return ("Token is either expired or invalid......");
			}

		} catch (FeignException e) {
			return ("Token is either expired or invalid......");

		}
		return service.cancelRequest(id, creditCardNumber);
	}

	/*
	 * GET: /TrackRequest (Input: RequestId | Output: string response as
	 * delivery date)
	 * http://localhost:8080/component/componentprocessingmodule/trackid/R002
	 */

	@GetMapping("/trackid/{id}")
	public String track(@PathVariable("id") String id,
			@RequestHeader(name = "Authorization", required = true) String token) throws InvalidTokenException {

		log.info(token);
		try {
			if (!authClient.validatingAuthorizationToken(token).isValidStatus()) {

				return ("Token is either expired or invalid...");
			}

		} catch (FeignException e) {
			return ("Token is either expired or invalid......");

		}
		return service.trackRequest(id);
	}

	@PostMapping(path = "/ProcessDetail", produces = MediaType.APPLICATION_JSON_VALUE)

	@ApiOperation(value = "to send the processRequestObj to the user", response = ProcessRequest.class, httpMethod = "POST")
	public ResponseEntity<ProcessResponse> processResponseDetails(@RequestBody ProcessRequest processRequestObj,
			@RequestHeader(name = "Authorization", required = true) String token) throws InvalidTokenException {

		log.info(token);
		try {
			if (!authClient.getsValidity(token).isValidStatus()) {

				throw new InvalidTokenException("Token is either expired or invalid...");
			}
		}

		catch (FeignException e) {
			throw new InvalidTokenException("Token is either expired or invalid...");

		}

		log.info("Checking if component is Integral or Accessory - BEGINS");
		if (("Integral").equalsIgnoreCase(processRequestObj.getComponentType())) {
			log.info("Retrieving the ProcessResponse object for Integral - BEGINS");

			processResponseObj = repairServiceImplObj.processService(processRequestObj, token);

			log.info("Retrieving the ProcessResponse object for Integral - ENDS");
		} else if (("Accessory").equalsIgnoreCase(processRequestObj.getComponentType())) {
			log.info("Retrieving the ProcessResponse object for Accessory - BEGINS");

			processResponseObj = replacementServiceImplObj.processService(processRequestObj, token);

			log.info("Retrieving the ProcessResponse object for Accessory - ENDS");
		} else {
			throw new ComponentTyepNotFoundException("Wrong Component Type");
		}

		log.info("Checking if component is Integral or Accessory - ENDS");

		return new ResponseEntity<>(processResponseObj, HttpStatus.OK);

	}

	@PostMapping(path = "/completeProcessing/{requestId}/{creditCardNumber}/{creditLimit}/{processingCharge}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentStatusDTO> messageConfirmation(@PathVariable String requestId,
			@PathVariable Integer creditCardNumber, @PathVariable Integer creditLimit,
			@PathVariable Integer processingCharge,
			@RequestHeader(name = "Authorization", required = true) String token) throws InvalidTokenException {

		log.info(token);
		try {
			if (authClient.getsValidity(token).isValidStatus()) {

				throw new InvalidTokenException("Token is either expired or invalid...");
			}

		} catch (FeignException e) {
			throw new InvalidTokenException("Token is either expired or invalid...");

		}
		log.info("Controller Component");
		try {
			return new ResponseEntity<>(new PaymentStatusDTO(replacementServiceImplObj.messageConfirmation(requestId,
					creditCardNumber, creditLimit, processingCharge, token)), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(new PaymentStatusDTO(replacementServiceImplObj.messageConfirmation(requestId,
					creditCardNumber, creditLimit, processingCharge, token)), HttpStatus.FORBIDDEN);
		}

	}

	@GetMapping(path = "/track/{id}")
	@ApiOperation(value = "to track the request", response = ProcessResponse.class, httpMethod = "GET")
	public ResponseEntity<String> trackRequest(@PathVariable("id") String id) {
		return new ResponseEntity<>(service.trackRequest(id), HttpStatus.OK);
	}

	@PostMapping(path = "/cancelRequest/{id}/{creditCardNumber}")
	@ApiOperation(value = "to cancel the request", response = ProcessResponse.class, httpMethod = "POST")
	public ResponseEntity<String> cancelRequest(@PathVariable("id") String id,
			@PathVariable("creditCardNumber") String creditCardNumber) {
		return new ResponseEntity<>(service.cancelRequest(id, creditCardNumber), HttpStatus.OK);
	}

	@GetMapping(path = "/health-check")
	public ResponseEntity<String> healthCheck() {
		log.info("ComponentProcessing Microservice is Up and Running....");
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

}
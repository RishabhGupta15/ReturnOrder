package com.componentprocessing.microservice.service;

import org.springframework.stereotype.Service;

import com.componentprocessing.microservice.model.ProcessRequest;
import com.componentprocessing.microservice.model.ProcessResponse;

@Service
public interface ProcessResponseService {
	public String trackRequest(String id);

	public String cancelRequest(String id,String creditCardNumber);
}

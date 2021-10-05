package com.componentprocessing.microservice.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.componentprocessing.microservice.model.ProcessResponse;
import com.componentprocessing.microservice.repository.ProcessResponseRepo;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProcessResponseServiceImpl implements ProcessResponseService {

	@Autowired
	private ProcessResponseRepo processResponseRepo;

	@Override
	public String trackRequest(String id) {
		// TODO Auto-generated method stub
		try {
			log.info("start");
			ProcessResponse r= processResponseRepo.findById(id).get();
			log.debug("response:{}", r);
			return "Delivery Date of the component=" + r.getDateOfDelivery();
		} catch (Exception e) {
			return "Invalid Id.Please Enter valid RequestID to track your item";
		}
	}

	@Override
	public String cancelRequest(String id,String creditCardNumber) {
		// TODO Auto-generated method stub
		SimpleDateFormat obj = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		try {
			log.info("start");
			String d = processResponseRepo.findById(id).get().getDateOfDelivery();
			LocalDate date = java.time.LocalDate.now();
			Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(d);

			Date d2 = new Date();
			long diff = TimeUnit.MILLISECONDS.toDays((d1.getTime() - d2.getTime()));
			if (diff == 0) {
				return "Requests cannot be cancelled 1 day prior to delivery date.";
			} else {
				processResponseRepo.deleteById(id);
				return "Cancellation of request with "+id+" successful";
			}
		} catch (Exception e) {
			return "Invalid Id..Please Enter valid RequestID to delete your request";
		}
	}

}

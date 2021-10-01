package com.payment.microservice.service;

import com.payment.microservice.dto.ReverseDTO;
import com.payment.microservice.exception.InvalidFormatException;
import com.payment.microservice.model.ReverseDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReverseServiceImp implements ReverseService {

    @Autowired
    ReverseDetails reverseDetails;
    @Override
    public ReverseDTO processReverseService(String requestId, Integer cardNumber, Integer cardLimit, Integer deductionAmount) {
        log.info("Reverse service started");
        try {
            reverseDetails.setCardNumber(cardNumber);
            reverseDetails.setRequestId(requestId);
            reverseDetails.setCardLimit(cardLimit+deductionAmount);
        } catch (Exception e){
            throw new InvalidFormatException("wrong type of input");
        }

        log.info("reverse service ended");
        return new ReverseDTO( (double)reverseDetails.getCardLimit());
    }
}

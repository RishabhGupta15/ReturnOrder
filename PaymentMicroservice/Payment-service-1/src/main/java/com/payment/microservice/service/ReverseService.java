package com.payment.microservice.service;

import com.payment.microservice.dto.ReverseDTO;
import org.springframework.stereotype.Service;

@Service
public interface ReverseService {
    public ReverseDTO processReverseService(String requestId, Integer cardNumber, Integer cardLimit, Integer deductionAmount);
}

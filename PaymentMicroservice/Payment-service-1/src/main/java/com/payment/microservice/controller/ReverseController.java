package com.payment.microservice.controller;

import com.payment.microservice.dto.ReverseDTO;
import com.payment.microservice.service.ReverseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reverse-payment")
public class ReverseController {

    @Autowired
    private ReverseService reverseServiceImp;

    @GetMapping("/{requestId}/{cardNo}/{cardLimit}/{deductionAmt}")
    public ResponseEntity<ReverseDTO>reversePayment(@PathVariable String requestId, @PathVariable int cardNo, @PathVariable int cardLimit, @PathVariable double deductionAmt){
        try {
            return new ResponseEntity<>(
                    reverseServiceImp.processReverseService(requestId, cardNo, cardLimit, (int)deductionAmt),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    reverseServiceImp.processReverseService(requestId, cardNo, cardLimit, (int)deductionAmt),
                    HttpStatus.FORBIDDEN);
        }
    }
}

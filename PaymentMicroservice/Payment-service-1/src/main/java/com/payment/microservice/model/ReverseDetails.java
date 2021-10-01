package com.payment.microservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ReverseDetails {
    private String requestId;
    private Integer cardNumber;
    private int cardLimit;
    private int deductionAmount;
}

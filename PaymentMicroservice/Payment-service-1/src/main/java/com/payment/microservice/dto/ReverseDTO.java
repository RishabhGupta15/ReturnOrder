package com.payment.microservice.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ReverseDTO {

    @JsonProperty
    private Double amount;

    @JsonIgnore
    public ReverseDTO(Double amount){
        this.amount = amount;
    }

    @JsonIgnore
    public void setAmount(Double amount){
        this.amount = amount;
    }

    @JsonIgnore
    public Double getAmount(){
        return amount;
    }
}

package com.example.demo.model.dtos;

import lombok.Data;

@Data
public class EnableCreditCardDTO {
    private String number;
    private Integer securityCode;
}

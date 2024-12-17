package com.example.demo.services.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListExtraInfoDTO {
    
    private String clave;
    private String valor;
}

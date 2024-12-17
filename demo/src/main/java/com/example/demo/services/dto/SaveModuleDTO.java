package com.example.demo.services.dto;

import lombok.Data;

@Data
public class SaveModuleDTO {

    private String name;
    private boolean audit;
    private boolean active;
}

package com.example.demo.services.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListModuleMicroservice {
    private String name;
    private boolean audit;
    private boolean active;
}

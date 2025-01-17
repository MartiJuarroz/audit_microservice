package com.example.demo.services.dto;

import java.util.HashMap;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListAuditLogs {

    private String action;
    private String actionTo;
    private String actionToId;
    private String date;

//    @DateTimeFormat(pattern = "dd-MM-YYYY' 'HH:mm:ss")
//    @JsonFormat(pattern = "dd-MM-YYYY' 'HH:mm:ss")
//    private LocalDateTime date;

    private String userId;
    private String entity;
    private String description;
    private Long moduleId;
    private HashMap<String, String> extraInfo;

}

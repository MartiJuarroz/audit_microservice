package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;
    private String actionTo;
    private String actionToId;
    private String userId;
    private String entity;
    private String description;
     
    @DateTimeFormat(pattern = "dd-MM-YYYY' 'HH:mm:ss")
    @JsonFormat(pattern = "dd-MM-YYYY' 'HH:mm:ss")
    private LocalDateTime date = LocalDateTime.now();

    private Long moduleId;

    @OneToMany(mappedBy = "auditLog")
    private List<ExtraInfo> extraInfo;

}

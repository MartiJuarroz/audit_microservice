package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.ValidateAdminUser;
import com.example.demo.services.AuditLogService;

@CrossOrigin
@RestController
@RequestMapping(path = "/auditLog")
public class AuditLogController {

    @Autowired
    AuditLogService auditLogService;

    @GetMapping(value = "")
    public ResponseEntity<?> findAll(
        @ValidateAdminUser @RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
        @RequestParam(required = false) String action,
        @RequestParam(required = false) String entity,
        @RequestParam(required = false) String dateFrom,
        @RequestParam(required = false) String dateTo,
        @RequestParam(required = false) String userId,
        @RequestParam(required = false) String moduleId
    ){
        return ResponseEntity.ok().body(auditLogService.findAuditLogs(action, entity, dateFrom, dateTo, userId, moduleId));
    }
    
}

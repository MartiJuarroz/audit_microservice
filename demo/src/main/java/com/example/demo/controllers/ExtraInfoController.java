package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.ValidateAdminUser;
import com.example.demo.services.ExtraInfoService;

@CrossOrigin
@RestController
@RequestMapping(path = "/extra-info")
public class ExtraInfoController {

    @Autowired
    ExtraInfoService extraInfoService;

    @GetMapping(value = "/{moduleId}")
    public ResponseEntity<?> findModuleExtraInfo(
        @ValidateAdminUser @RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
        @PathVariable("moduleId") String moduleId
    ){
        return ResponseEntity.ok().body(extraInfoService.findModuleExtraInfo(moduleId));
    }
    
}

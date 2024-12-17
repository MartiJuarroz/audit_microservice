package com.example.demo.controllers;

import com.example.demo.security.ValidateAdminUser;
import com.example.demo.services.ModuleMicroserviceService;
import com.example.demo.services.dto.SaveModuleDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/module")
public class ModuleMicroserviceController {

    @Autowired
    ModuleMicroserviceService moduleMicroserviceService;

    @GetMapping(value = "/")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok().body(moduleMicroserviceService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id){
        return ResponseEntity.ok().body(moduleMicroserviceService.findById(id));
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> create(
            @ValidateAdminUser @RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
            @RequestBody SaveModuleDTO module){
        return ResponseEntity.ok().body(moduleMicroserviceService.create(module));
    }

    @PutMapping(value = "/{id}/activate")
    public ResponseEntity<?> enable(@ValidateAdminUser @RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                    @PathVariable("id") String id){
        return ResponseEntity.ok().body(moduleMicroserviceService.enable(id));
    }

    @PutMapping(value = "/{id}/deactivate")
    public ResponseEntity<?> disable(@ValidateAdminUser @RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                     @PathVariable("id") String id){
        return ResponseEntity.ok().body(moduleMicroserviceService.disable(id));
    }

    @PutMapping(value = "/{id}/enable")
    public ResponseEntity<?> enableModule(@ValidateAdminUser @RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @PathVariable("id") String moduleId){
        return ResponseEntity.ok().body(moduleMicroserviceService.enableModule(moduleId));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> disableModule(@ValidateAdminUser @RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @PathVariable("id") String moduleId){
        return ResponseEntity.ok().body(moduleMicroserviceService.deleteModule(moduleId, auth));
    }
}

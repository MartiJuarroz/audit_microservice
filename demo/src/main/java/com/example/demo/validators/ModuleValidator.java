package com.example.demo.validators;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ModuleMicroservice;
import com.example.demo.repositories.ModuleMicroserviceRepo;

import lombok.Data;

@Data
@Service
public class ModuleValidator {

    @Autowired
    ModuleMicroserviceRepo moduleMicroserviceRepo;

    public void validateNameModule(ModuleMicroservice module){
        HashMap<String, String> errors = new HashMap<>();
        
    }

}

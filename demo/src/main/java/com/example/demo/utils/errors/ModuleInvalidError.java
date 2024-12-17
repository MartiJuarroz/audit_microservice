package com.example.demo.utils.errors;

import lombok.Data;

import java.util.HashMap;

@Data
public class ModuleInvalidError extends Error{

    private HashMap<String, String> errors;
    public ModuleInvalidError(String message) {
        super(message);
    }
}

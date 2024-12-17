package com.example.demo.controllers;

import com.example.demo.utils.errors.ModuleInvalidError;
import com.example.demo.utils.errors.RecordNotFoundExcepcion;
import com.example.demo.utils.errors.UserUnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandleErrors {

    @ExceptionHandler({ ModuleInvalidError.class })
    public ResponseEntity<?> handleModuleInvalid(ModuleInvalidError er) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(er.getErrors());
    }

    @ExceptionHandler({ UserUnauthorizedException.class })
    public ResponseEntity<?> handleUnauthorizedUser(UserUnauthorizedException er) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(er.getMessage());
    }

    @ExceptionHandler({ RecordNotFoundExcepcion.class })
    public ResponseEntity<?> handleRecordNotFound(RecordNotFoundExcepcion er) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(er.getMessage());
    }
}

package com.example.demo.server;

import org.springframework.stereotype.Service;

import java.util.logging.Level;

@Service
public class AuditLogger {
    private final java.util.logging.Logger logger = java.util.logging.Logger.getLogger("audit");

    public void info(String text) {
        logger.log(Level.INFO, text);
    }

    public void error(String text, Exception e) {
        logger.log(Level.SEVERE, text, e);
    }
}

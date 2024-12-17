package com.example.demo.utils.errors;

public class UserUnauthorizedException extends Error{
    public UserUnauthorizedException(String message) {
        super(message);
    }
}

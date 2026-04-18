package com.example.healthClaims.exception;

public class ClaimNotFoundException extends RuntimeException{
    public ClaimNotFoundException(String message){
        super(message);
    }
}

package com.example.turfMgmt.Exception;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String message){
        super(message);
    }
}

package com.example.turfMgmt.Exception;

public class BookingModificationNotAllowedException extends RuntimeException{
    public BookingModificationNotAllowedException(String message){
        super(message);
    }
}

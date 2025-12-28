package com.example.turfMgmt.DTOs;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookingRequest {

    private Long turfId;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private Integer numberOfPeople;
}

package com.example.turfMgmt.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingResponse {
    private Long bookingId;
    private String turfName;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private Integer numberOfPeople;
    private Double totalCost;
}

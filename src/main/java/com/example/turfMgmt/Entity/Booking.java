package com.example.turfMgmt.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "turf_id",nullable = false)
    private Turf turf;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;

    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;

    private Integer totalNights;
    private Integer numberOfPeople;

    private Double pricePerNight;
    private Double totalCost;
}

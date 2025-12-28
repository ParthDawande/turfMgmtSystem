package com.example.turfMgmt.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    private String title;

    @Column(length = 1000)
    private String description;

    private String contactPreference;

    @Enumerated(EnumType.STRING)
    private CompalintStatus status;

    @ManyToOne
    @JoinColumn(name = "booking_id",nullable = false)
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;

    private LocalDateTime createdAt;

    public void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.status = CompalintStatus.OPEN;
    }
}

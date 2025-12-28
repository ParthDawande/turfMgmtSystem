package com.example.turfMgmt.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Turf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String turfName;
    private String location;
    private String turfType;
    private Double pricePerNight;
    private Integer maxPeople;

    @OneToMany(mappedBy = "turf", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Booking> bookings;



}

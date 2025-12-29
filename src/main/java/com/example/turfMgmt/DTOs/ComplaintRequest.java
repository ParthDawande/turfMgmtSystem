package com.example.turfMgmt.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplaintRequest {
    private Long bookingId;
    private String category;
    private String title;
    private String description;
    private String contactPreference;
}

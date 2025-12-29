package com.example.turfMgmt.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintResponse {
    private Long complaintId;
    private String category;
    private String title;
    private String description;
    private String status;
    private String bookingId;
    private LocalDateTime createdAt;
}

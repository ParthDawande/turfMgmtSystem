package com.example.turfMgmt.Service;

import com.example.turfMgmt.DTOs.ComplaintRequest;
import com.example.turfMgmt.DTOs.ComplaintResponse;

import java.util.List;

public interface ComplaintService {
    ComplaintResponse createComplaint(Long userId,ComplaintRequest request);

    List<ComplaintResponse> getMyComplaints();

    List<ComplaintResponse> getAllComplaints();
}

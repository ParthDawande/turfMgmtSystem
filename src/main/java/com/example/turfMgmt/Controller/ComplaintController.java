package com.example.turfMgmt.Controller;

import com.example.turfMgmt.DTOs.ComplaintRequest;
import com.example.turfMgmt.DTOs.ComplaintResponse;
import com.example.turfMgmt.Service.ComplaintService;
import com.example.turfMgmt.util.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping
    public ResponseEntity<ComplaintResponse> createComplaint(@RequestBody ComplaintRequest request){
        Long userId = SecurityUtil.getCurrentUserId();
        return ResponseEntity.ok(complaintService.createComplaint(userId,request));
    }

    @GetMapping("/my")
    public ResponseEntity<List<ComplaintResponse>> myComplaints(){
        return ResponseEntity.ok(complaintService.getMyComplaints());
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ComplaintResponse>> allComplaints(){
        return ResponseEntity.ok(complaintService.getAllComplaints());
    }
}

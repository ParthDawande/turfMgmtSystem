package com.example.turfMgmt.Service.Impl;

import com.example.turfMgmt.DTOs.ComplaintRequest;
import com.example.turfMgmt.DTOs.ComplaintResponse;
import com.example.turfMgmt.Entity.Booking;
import com.example.turfMgmt.Entity.Complaint;
import com.example.turfMgmt.Entity.Customer;
import com.example.turfMgmt.Repository.BookingRepository;
import com.example.turfMgmt.Repository.ComplaintRepository;
import com.example.turfMgmt.Repository.CustomerRepository;
import com.example.turfMgmt.Service.ComplaintService;
import com.example.turfMgmt.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;

    public ComplaintServiceImpl(ComplaintRepository complaintRepository, BookingRepository bookingRepository, CustomerRepository customerRepository) {
        this.complaintRepository = complaintRepository;
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public ComplaintResponse createComplaint(Long userId,ComplaintRequest request) {


        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(()-> new RuntimeException("Booking not found"));

        if(!booking.getCustomer().getId().equals(userId)){
            throw new RuntimeException("You can complain only for your booking");
        }

        Customer customer = customerRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("Customer not found"));

        Complaint complaint = new Complaint();
        complaint.setCategory(request.getCategory());
        complaint.setTitle(request.getTitle());
        complaint.setDescription(request.getDescription());
        complaint.setContactPreference(request.getContactPreference());
        complaint.setBooking(booking);
        complaint.setCustomer(customer);

        Complaint savedComplaint = complaintRepository.save(complaint);

        return mapToResponse(savedComplaint);
    }

    @Override
    public List<ComplaintResponse> getMyComplaints() {
        Long userId = SecurityUtil.getCurrentUserId();

        return complaintRepository.findByCustomerId(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<ComplaintResponse> getAllComplaints() {
        return complaintRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ComplaintResponse mapToResponse(Complaint complaint){
        ComplaintResponse response = new ComplaintResponse();
        response.setComplaintId(complaint.getId());
        response.setCategory(complaint.getCategory());
        response.setTitle(complaint.getTitle());
        response.setDescription(complaint.getDescription());
        response.setStatus(complaint.getStatus().name());
        response.setBookingId(complaint.getBooking().getId().toString());
        response.setCreatedAt(complaint.getCreatedAt());
        return response;
    }
}

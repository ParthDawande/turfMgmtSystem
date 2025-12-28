package com.example.turfMgmt.Controller;

import com.example.turfMgmt.DTOs.BookingRequest;
import com.example.turfMgmt.DTOs.BookingResponse;
import com.example.turfMgmt.Entity.Booking;
import com.example.turfMgmt.Entity.Turf;
import com.example.turfMgmt.Service.BookingService;
import com.example.turfMgmt.util.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest request){

        Long customerId = SecurityUtil.getCurrentUserId();
        Booking booking = new Booking();
        booking.setCheckInDate(request.getCheckInDate());
        booking.setCheckOutDate(request.getCheckOutDate());
        booking.setNumberOfPeople(request.getNumberOfPeople());

        Turf turf = new Turf();
        turf.setId(request.getTurfId());
        booking.setTurf(turf);

        Booking savedBooking = bookingService.createBooking(customerId,booking);
        BookingResponse response = new BookingResponse();
        response.setBookingId(savedBooking.getId());
        response.setTurfName(savedBooking.getTurf().getTurfName());
        response.setCheckInDate(savedBooking.getCheckInDate());
        response.setCheckOutDate(savedBooking.getCheckOutDate());
        response.setNumberOfPeople(savedBooking.getNumberOfPeople());
        response.setTotalCost(savedBooking.getTotalCost());

        return ResponseEntity.ok(
                response
        );
    }

    @GetMapping("/me")
    public ResponseEntity<List<BookingResponse>> getMyBookings(){
        Long customerId = SecurityUtil.getCurrentUserId();
        List<BookingResponse> bookingResponses = new ArrayList<>();
        List<Booking> bookings = bookingService.getMyBookings(customerId);

        for(int i=0;i<bookings.size();i++){
            Booking booking = bookings.get(i);
            BookingResponse response = new BookingResponse();
            response.setBookingId(booking.getId());
            response.setTurfName(booking.getTurf().getTurfName());
            response.setCheckInDate(booking.getCheckInDate());
            response.setCheckOutDate(booking.getCheckOutDate());
            response.setNumberOfPeople(booking.getNumberOfPeople());
            response.setTotalCost(booking.getTotalCost());
            bookingResponses.add(response);
        }
        return ResponseEntity.ok(
                bookingResponses
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResponse> updateBooking(@PathVariable Long id,@RequestBody Booking booking) throws Exception {
        Booking newbooking = bookingService.updateBooking(id,booking);
        BookingResponse bookingResponse = new BookingResponse();
        bookingResponse.setBookingId(newbooking.getId());
        bookingResponse.setTurfName(newbooking.getTurf().getTurfName());
        bookingResponse.setCheckInDate(newbooking.getCheckInDate());
        bookingResponse.setCheckOutDate(newbooking.getCheckOutDate());
        bookingResponse.setNumberOfPeople(newbooking.getNumberOfPeople());
        bookingResponse.setTotalCost(newbooking.getTotalCost());
        return ResponseEntity.ok(bookingResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) throws Exception {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok("Booking cancelled successfully.");
    }
}

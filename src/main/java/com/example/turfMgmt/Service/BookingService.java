package com.example.turfMgmt.Service;

import com.example.turfMgmt.Entity.Booking;

import java.util.List;

public interface BookingService {

    Booking createBooking(Long customerId,Booking booking);

    List<Booking> getMyBookings(Long customerId);

    Booking updateBooking(Long bookingId,Booking updatedBooking) throws Exception;

    void deleteBooking(Long bookingId) throws Exception;
}

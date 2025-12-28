package com.example.turfMgmt.Service.Impl;

import com.example.turfMgmt.Entity.Booking;
import com.example.turfMgmt.Entity.Customer;
import com.example.turfMgmt.Entity.Turf;
import com.example.turfMgmt.Exception.BookingModificationNotAllowedException;
import com.example.turfMgmt.Exception.ResourceNotFoundException;
import com.example.turfMgmt.Repository.BookingRepository;
import com.example.turfMgmt.Repository.CustomerRepository;
import com.example.turfMgmt.Repository.TurfRepository;
import com.example.turfMgmt.Service.BookingService;
import com.example.turfMgmt.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final TurfRepository turfRepository;
    private final CustomerRepository customerRepository;

    public BookingServiceImpl(BookingRepository bookingRepository,TurfRepository turfRepository,CustomerRepository customerRepository){
        this.bookingRepository = bookingRepository;
        this.turfRepository = turfRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public Booking createBooking(Long customerId, Booking booking) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Turf turf = turfRepository.findById(booking.getTurf().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Turf not found"));

        long nights = ChronoUnit.DAYS.between(
                booking.getCheckInDate(),
                booking.getCheckOutDate()
        );

        booking.setCustomer(customer);
        booking.setTurf(turf);
        booking.setTotalNights((int) nights);
        booking.setPricePerNight(turf.getPricePerNight());
        booking.setTotalCost(nights*turf.getPricePerNight());

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getMyBookings(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }

    @Override
    public Booking updateBooking(Long bookingId, Booking updatedBooking) throws Exception {
        Booking existing = getBookingOrThrow(bookingId);

        validateBookingAccess(existing);

        validateBookingModification(existing);

        existing.setCheckInDate(updatedBooking.getCheckInDate());
        existing.setCheckOutDate(updatedBooking.getCheckOutDate());
        existing.setNumberOfPeople(updatedBooking.getNumberOfPeople());

        long nights = ChronoUnit.DAYS.between(
                existing.getCheckInDate(),
                existing.getCheckOutDate()
        );

        existing.setTotalCost(nights * existing.getPricePerNight());

        return bookingRepository.save(existing);
    }

    @Override
    public void deleteBooking(Long bookingId) throws Exception {
        Booking booking = getBookingOrThrow(bookingId);

        validateBookingAccess(booking);

        validateBookingModification(booking);

        bookingRepository.delete(booking);
    }

    private void validateBookingAccess(Booking booking) throws Exception {

        Long loggedInUserId = SecurityUtil.getCurrentUserId();
        String role = SecurityUtil.getCurrentUserRole();

        if ("ROLE_ADMIN".equals(role)) {
            return;
        }

        if(!booking.getCustomer().getId().equals(loggedInUserId)){
            throw new AccessDeniedException("You are not allowed to access this booking");
        }
    }

    private void validateBookingModification(Booking booking){
        long hoursLeft = Duration.between(
                LocalDateTime.now(),
                booking.getCheckInDate()
        ).toHours();

        if(hoursLeft<24){
            throw new BookingModificationNotAllowedException("Booking can be updated or cancelled only 24 hours before check-in");
        }
    }

    private Booking getBookingOrThrow(Long id){
        return bookingRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Booking not found"));
    }
}

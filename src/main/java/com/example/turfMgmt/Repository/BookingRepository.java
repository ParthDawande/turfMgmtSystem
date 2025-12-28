package com.example.turfMgmt.Repository;

import com.example.turfMgmt.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    List<Booking> findByCustomerId(Long customerId);
}

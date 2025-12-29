package com.example.turfMgmt.Repository;

import com.example.turfMgmt.Entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint,Long> {
    List<Complaint> findByCustomerId(Long customerId);
}

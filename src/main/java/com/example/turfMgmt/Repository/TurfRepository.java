package com.example.turfMgmt.Repository;

import com.example.turfMgmt.Entity.Turf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurfRepository extends JpaRepository<Turf,Long> {
}

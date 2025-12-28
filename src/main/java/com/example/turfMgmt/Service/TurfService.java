package com.example.turfMgmt.Service;

import com.example.turfMgmt.Entity.Turf;

import java.util.List;

public interface TurfService {

    Turf createTurf(Turf turf);

    Turf updateTurf(Long id,Turf turf);

    Turf getTurfById(Long id);

    List<Turf> getAllTurfs();

    void deleteTurf(Long id);
}

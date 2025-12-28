package com.example.turfMgmt.Service.Impl;

import com.example.turfMgmt.Entity.Turf;
import com.example.turfMgmt.Exception.ResourceNotFoundException;
import com.example.turfMgmt.Repository.TurfRepository;
import com.example.turfMgmt.Service.TurfService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurfServiceImpl implements TurfService {

    private final TurfRepository turfRepository;

    public TurfServiceImpl(TurfRepository turfRepository){
        this.turfRepository = turfRepository;
    }


    @Override
    public Turf createTurf(Turf turf) {
        return turfRepository.save(turf);
    }

    @Override
    public Turf updateTurf(Long id, Turf updatedTurf) {
        Turf existing = turfRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turf not found"));

        existing.setTurfName(updatedTurf.getTurfName());
        existing.setLocation(updatedTurf.getLocation());
        existing.setTurfType(updatedTurf.getTurfType());
        existing.setPricePerNight(updatedTurf.getPricePerNight());
        existing.setMaxPeople(updatedTurf.getMaxPeople());

        return turfRepository.save(existing);
    }

    @Override
    public Turf getTurfById(Long id) {
        return turfRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turf not found"));
    }

    @Override
    public List<Turf> getAllTurfs() {
        return turfRepository.findAll();
    }

    @Override
    public void deleteTurf(Long id) {
        Turf turf = turfRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turf not found"));

        turfRepository.delete(turf);
    }
}

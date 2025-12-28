package com.example.turfMgmt.Controller;

import com.example.turfMgmt.Entity.Turf;
import com.example.turfMgmt.Service.TurfService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/turfs")
public class TurfController {

    private final TurfService turfService;

    public TurfController(TurfService turfService){
        this.turfService = turfService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Turf> createTurf(@RequestBody Turf turf){
        return ResponseEntity.ok(turfService.createTurf(turf));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Turf> updateTurf(@PathVariable Long id,@RequestBody Turf turf){
        return ResponseEntity.ok(turfService.updateTurf(id,turf));
    }

    @GetMapping
    public ResponseEntity<List<Turf>> getAllUsers(){
        return ResponseEntity.ok(turfService.getAllTurfs());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Turf> getTurfById(@PathVariable Long id){
        return ResponseEntity.ok(turfService.getTurfById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTurf(@PathVariable Long id) {
        turfService.deleteTurf(id);
        return ResponseEntity.ok("Turf deleted successfully");
    }

}

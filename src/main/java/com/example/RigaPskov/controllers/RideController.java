package com.example.RigaPskov.controllers;

import com.example.RigaPskov.entities.Ride;
import com.example.RigaPskov.services.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rides")
public class RideController {

    @Autowired
    private RideService rideService;

    @GetMapping
    public List<Ride> getAllRides() {
        return rideService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ride> getRideById(@PathVariable Long id) {
        Optional<Ride> ride = rideService.findById(id);
        if (ride.isPresent()) {
            return new ResponseEntity<>(ride.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Ride> createRide(@RequestBody Ride ride,
                                           @RequestParam Long carLatviaId,
                                           @RequestParam Long carRussiaId) {
        Ride savedRide = rideService.save(ride, carLatviaId, carRussiaId);
        return new ResponseEntity<>(savedRide, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRide(@PathVariable Long id) {
        Optional<Ride> ride = rideService.findById(id);
        if (ride.isPresent()) {
            rideService.delete(ride.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{rideId}/assign/carLatvia/{carIdLatvia}")
    public ResponseEntity<Void> assignCarLatvia(@PathVariable Long rideId, @PathVariable Long carIdLatvia) {
        rideService.assignCarLatvia(rideId, carIdLatvia);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{rideId}/assign/carRussia/{carIdRussia}")
    public ResponseEntity<Void> assignCarRussia(@PathVariable Long rideId, @PathVariable Long carIdRussia) {
        rideService.assignCarRussia(rideId, carIdRussia);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

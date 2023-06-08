package com.example.RigaPskov.services;

import com.example.RigaPskov.entities.CarLatvia;
import com.example.RigaPskov.entities.CarRussia;
import com.example.RigaPskov.entities.Ride;
import com.example.RigaPskov.repositories.CarLatviaRepository;
import com.example.RigaPskov.repositories.CarRussiaRepository;
import com.example.RigaPskov.repositories.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private CarLatviaRepository carLatviaRepository;

    @Autowired
    private CarRussiaRepository carRussiaRepository;

    public List<Ride> findAll() {
        return rideRepository.findAll();
    }

    public Optional<Ride> findById(Long id) {
        return rideRepository.findById(id);
    }

    public Ride save(Ride ride, Long carLatviaId, Long carRussiaId) {
        Optional<CarLatvia> carLatviaOptional = carLatviaRepository.findById(carLatviaId);
        Optional<CarRussia> carRussiaOptional = carRussiaRepository.findById(carRussiaId);

        if (carLatviaOptional.isPresent() && carRussiaOptional.isPresent()) {
            ride.setCarLatvia(carLatviaOptional.get());
            ride.setCarRussia(carRussiaOptional.get());
        }
        return rideRepository.save(ride);
    }

    public void delete(Ride ride) {
        rideRepository.delete(ride);
    }

    public void assignCarLatvia(Long rideId, Long carIdLatvia) {
        Optional<Ride> rideOpt = rideRepository.findById(rideId);
        Optional<CarLatvia> carLatviaOpt = carLatviaRepository.findById(carIdLatvia);

        if (rideOpt.isPresent() && carLatviaOpt.isPresent()) {
            Ride ride = rideOpt.get();
            CarLatvia carLatvia = carLatviaOpt.get();

            ride.setCarLatvia(carLatvia);

            rideRepository.save(ride);
        } else {
            // handle the case when either the ride or the car doesn't exist
        }
    }

    public void assignCarRussia(Long rideId, Long carIdRussia) {
        Optional<Ride> rideOpt = rideRepository.findById(rideId);
        Optional<CarRussia> carRussiaOpt = carRussiaRepository.findById(carIdRussia);

        if (rideOpt.isPresent() && carRussiaOpt.isPresent()) {
            Ride ride = rideOpt.get();
            CarRussia carRussia = carRussiaOpt.get();

            ride.setCarRussia(carRussia);

            rideRepository.save(ride);
        } else {
            // handle the case when either the ride or the car doesn't exist
        }
    }

}

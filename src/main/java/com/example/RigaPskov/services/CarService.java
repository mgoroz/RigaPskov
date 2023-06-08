package com.example.RigaPskov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.RigaPskov.entities.CarLatvia;
import com.example.RigaPskov.entities.CarRussia;
import com.example.RigaPskov.repositories.CarLatviaRepository;
import com.example.RigaPskov.repositories.CarRussiaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarLatviaRepository carLatviaRepository;

    @Autowired
    private CarRussiaRepository carRussiaRepository;

    public List<CarLatvia> findAllLatvia() {
        return carLatviaRepository.findAll();
    }

    public List<CarRussia> findAllRussia() {
        return carRussiaRepository.findAll();
    }

    public Optional<CarLatvia> findByIdLatvia(Long id) {
        return carLatviaRepository.findById(id);
    }

    public Optional<CarRussia> findByIdRussia(Long id) {
        return carRussiaRepository.findById(id);
    }

    public CarLatvia saveLatvia(CarLatvia carLatvia) {
        return carLatviaRepository.save(carLatvia);
    }

    public CarRussia saveRussia(CarRussia carRussia) {
        return carRussiaRepository.save(carRussia);
    }

    public void deleteLatvia(Long id) {
        carLatviaRepository.deleteById(id);
    }

    public void deleteRussia(Long id) {
        carRussiaRepository.deleteById(id);
    }
}

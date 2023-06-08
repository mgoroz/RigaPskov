package com.example.RigaPskov.controllers;

import com.example.RigaPskov.entities.CarLatvia;
import com.example.RigaPskov.entities.CarRussia;
import com.example.RigaPskov.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    // Получение всех машин из Латвии
    @GetMapping("/latvia")
    public ResponseEntity<List<CarLatvia>> getAllCarsLatvia() {
        return ResponseEntity.ok(carService.findAllLatvia());
    }

    // Получение всех машин из России
    @GetMapping("/russia")
    public ResponseEntity<List<CarRussia>> getAllCarsRussia() {
        return ResponseEntity.ok(carService.findAllRussia());
    }

    // Добавление новой машины в Латвию
    @PostMapping("/latvia")
    public ResponseEntity<CarLatvia> addCarLatvia(@RequestBody CarLatvia carLatvia) {
        return ResponseEntity.ok(carService.saveLatvia(carLatvia));
    }

    // Добавление новой машины в Россию
    @PostMapping("/russia")
    public ResponseEntity<CarRussia> addCarRussia(@RequestBody CarRussia carRussia) {
        return ResponseEntity.ok(carService.saveRussia(carRussia));
    }

    // Удаление машины из Латвии по ID
    @DeleteMapping("/latvia/{id}")
    public ResponseEntity<Void> deleteCarLatvia(@PathVariable Long id) {
        carService.deleteLatvia(id);
        return ResponseEntity.ok().build();
    }

    // Удаление машины из России по ID
    @DeleteMapping("/russia/{id}")
    public ResponseEntity<Void> deleteCarRussia(@PathVariable Long id) {
        carService.deleteRussia(id);
        return ResponseEntity.ok().build();
    }
}

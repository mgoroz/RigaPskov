package com.example.RigaPskov.controllers;

import com.example.RigaPskov.entities.AvailableRide;
import com.example.RigaPskov.services.AvailableRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("/api/availableRides")
public class AvailableRideController {

    private final AvailableRideService availableRideService;

    @Autowired
    public AvailableRideController(AvailableRideService availableRideService) {
        this.availableRideService = availableRideService;
    }

    // Получение всех доступных поездок
    @GetMapping
    public ResponseEntity<List<AvailableRide>> getAllAvailableRides() {
        return ResponseEntity.ok(availableRideService.findAll());
    }

    // Добавление новой доступной поездки
    @PostMapping
    public ResponseEntity<AvailableRide> addAvailableRide(@RequestBody AvailableRide availableRide) {
        return ResponseEntity.ok(availableRideService.save(availableRide));
    }

    // Автоматическое добавление доступных поездок на заданный период времени
    @PostMapping("/autoAdd")
    public ResponseEntity<Void> addAutomaticRides(
            @RequestParam String direction,
            @RequestParam Time time,
            @RequestParam int daysInFuture) {
        availableRideService.addAutomaticRides(direction, time, daysInFuture);
        return ResponseEntity.ok().build();
    }
}

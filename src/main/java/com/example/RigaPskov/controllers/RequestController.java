package com.example.RigaPskov.controllers;

import com.example.RigaPskov.entities.Request;
import com.example.RigaPskov.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @GetMapping
    public List<Request> getAllRequests() {
        return requestService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable Long id) {
        Optional<Request> request = requestService.findById(id);
        if (request.isPresent()) {
            return new ResponseEntity<>(request.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Request> createRequest(@RequestBody Request request) {
        Request savedRequest = requestService.save(request);
        return new ResponseEntity<>(savedRequest, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        Optional<Request> request = requestService.findById(id);
        if (request.isPresent()) {
            requestService.delete(request.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Request> updateRequest(@PathVariable Long id, @RequestBody Request updatedRequest) {
        Optional<Request> request = requestService.findById(id);
        if (request.isPresent()) {
            Request existingRequest = request.get();
            existingRequest.setDate(updatedRequest.getDate());
            existingRequest.setTime(updatedRequest.getTime());
            existingRequest.setDirection(updatedRequest.getDirection());
            existingRequest.setType(updatedRequest.getType());
            existingRequest.setPassengers(updatedRequest.getPassengers());
            requestService.save(existingRequest);
            return new ResponseEntity<>(existingRequest, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/process")
    public ResponseEntity<Void> processRequest(@PathVariable Long id) {
        Optional<Request> request = requestService.findById(id);
        if (request.isPresent()) {
            requestService.processRequest(request.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

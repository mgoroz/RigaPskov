package com.example.RigaPskov.services;

import com.example.RigaPskov.entities.Request;
import com.example.RigaPskov.entities.Ride;
import com.example.RigaPskov.repositories.RequestRepository;
import com.example.RigaPskov.repositories.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private RideRepository rideRepository;

    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    public Optional<Request> findById(Long id) {
        return requestRepository.findById(id);
    }

    public List<Request> findByDate(Date date) {
        return requestRepository.findAll().stream()
                .filter(request -> request.getDate().equals(date))
                .collect(Collectors.toList());
    }

    public List<Request> findByTime(Time time) {
        return requestRepository.findAll().stream()
                .filter(request -> request.getTime().equals(time))
                .collect(Collectors.toList());
    }

    public Request save(Request request) {
        return requestRepository.save(request);
    }

    public void delete(Request request) {
        requestRepository.delete(request);
    }

    public void processRequest(Request request) {
        Ride ride = null;
        // check if request type is 'package'
        if(request.getType() == Request.Type.PACKAGE){
            // for package requests, just find the first ride with the same date, time, and direction
            ride = rideRepository.findFirstByDateAndTimeAndDirection(
                    request.getDate(),
                    request.getTime(),
                    request.getDirection());
        } else {
            // for person requests, find the first ride with the same date, time, direction and enough seats
            ride = rideRepository.findFirstByDateAndTimeAndDirectionAndSeatsLeftGreaterThan(
                    request.getDate(),
                    request.getTime(),
                    request.getDirection(),
                    request.getPassengers());
        }

        // if no suitable ride found, create a new one
        if (ride == null) {
            ride = new Ride(
                    request.getDate(),
                    request.getTime(),
                    request.getDirection(),
                    4 - request.getPassengers() // 4 seats in a car, subtract passengers
            );
            ride = rideRepository.saveAndFlush(ride);
        }
        else if(request.getType() == Request.Type.PERSON){
            // if ride found and request type is 'person', decrease available seats
            ride.setSeatsLeft(ride.getSeatsLeft() - request.getPassengers());
            ride = rideRepository.saveAndFlush(ride);
        }

        // link request and ride
        request.setRide(ride);
        requestRepository.saveAndFlush(request);
    }

    @Scheduled(fixedRate = 60000) // Каждые 60 секунд будет выполняться поиск необработанных заказов
    public void processUnprocessedRequests() {
        List<Request> unprocessedRequests = requestRepository.findAllUnprocessed();
        for (Request request : unprocessedRequests) {
            processRequest(request);
        }
    }
}

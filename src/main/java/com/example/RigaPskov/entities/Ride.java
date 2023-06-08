package com.example.RigaPskov.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "rideId")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "Rides")
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ride_id")
    private Long rideId;

    @Column(name = "date")
    private Date date;

    @Column(name = "time")
    private Time time;

    @Column(name = "direction")
    private String direction;

    @Column(name = "seats_left")
    private int seatsLeft;

    @ManyToOne
    @JoinColumn(name = "car_id_latvia")
    private CarLatvia carLatvia;

    @ManyToOne
    @JoinColumn(name = "car_id_russia")
    private CarRussia carRussia;

    @OneToMany(mappedBy = "ride", cascade = CascadeType.ALL)
    private List<Request> requests = new ArrayList<>();

    public Ride() {
    }

    public Ride(Long rideId, Date date, Time time, String direction, int seatsLeft, CarLatvia carLatvia, CarRussia carRussia, List<Request> requests) {
        this.rideId = rideId;
        this.date = date;
        this.time = time;
        this.direction = direction;
        this.seatsLeft = seatsLeft;
        this.carLatvia = carLatvia;
        this.carRussia = carRussia;
        this.requests = requests;
    }

    public Ride(Date date, Time time, String direction, int seatsLeft, CarLatvia carLatvia, CarRussia carRussia, List<Request> requests) {
        this.date = date;
        this.time = time;
        this.direction = direction;
        this.seatsLeft = seatsLeft;
        this.carLatvia = carLatvia;
        this.carRussia = carRussia;
        this.requests = requests;
    }

    public Ride(Date date, Time time, String direction, int seatsLeft) {
        this.date = date;
        this.time = time;
        this.direction = direction;
        this.seatsLeft = seatsLeft;
    }

    // Getters and setters
    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getSeatsLeft() {
        return seatsLeft;
    }

    public void setSeatsLeft(int seatsLeft) {
        this.seatsLeft = seatsLeft;
    }

    public CarLatvia getCarLatvia() {
        return carLatvia;
    }

    public void setCarLatvia(CarLatvia carLatvia) {
        this.carLatvia = carLatvia;
    }

    public CarRussia getCarRussia() {
        return carRussia;
    }

    public void setCarRussia(CarRussia carRussia) {
        this.carRussia = carRussia;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}

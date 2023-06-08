package com.example.RigaPskov.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "AvailableRides")
public class AvailableRide {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ride_id;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Time time;

    @Column(nullable = false)
    private String direction;

    public AvailableRide() {
    }

    public AvailableRide(Integer ride_id, Date date, Time time, String direction) {
        this.ride_id = ride_id;
        this.date = date;
        this.time = time;
        this.direction = direction;
    }

    public AvailableRide(Date date, Time time, String direction) {
        this.date = date;
        this.time = time;
        this.direction = direction;
    }

    public Integer getRide_id() {
        return ride_id;
    }

    public void setRide_id(Integer ride_id) {
        this.ride_id = ride_id;
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
}

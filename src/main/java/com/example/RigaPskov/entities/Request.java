package com.example.RigaPskov.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Time;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "requestId")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "Requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long requestId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "date")
    private java.sql.Date date;

    @Column(name = "time")
    private Time time;

    @Column(name = "direction")
    private String direction;

    @Column(name = "type")
    private Type type;

    @Column(name = "passengers")
    private Integer passengers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ride_id")
    private Ride ride;

    public enum Type {
        PERSON("person"),
        PACKAGE("package");

        private String value;

        Type(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @JsonCreator
        public static Type fromValue(String value) {
            for (Type type : Type.values()) {
                if (type.getValue().equals(value)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Invalid type value: " + value);
        }
    }


    public Request() {
    }

    public Request(Long requestId,
                   Long userId,
                   Date date,
                   Time time,
                   String direction,
                   Type type,
                   Integer passengers,
                   Ride ride) {
        this.requestId = requestId;
        this.userId = userId;
        this.date = date;
        this.time = time;
        this.direction = direction;
        this.type = type;
        this.passengers = passengers;
        this.ride = ride;
    }

    public Request(Long userId,
                   Date date,
                   Time time,
                   String direction,
                   Type type,
                   Integer passengers,
                   Ride ride) {
        this.userId = userId;
        this.date = date;
        this.time = time;
        this.direction = direction;
        this.type = type;
        this.passengers = passengers;
        this.ride = ride;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getPassengers() {
        return passengers;
    }

    public void setPassengers(Integer passengers) {
        this.passengers = passengers;
    }
}

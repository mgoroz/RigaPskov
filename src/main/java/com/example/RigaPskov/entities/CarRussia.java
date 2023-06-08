package com.example.RigaPskov.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "CarsRussia")
public class CarRussia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long carId;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "available")
    private Boolean available;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "driver_phone")
    private String driverPhone;

    // Getters and setters...


    public CarRussia() {
    }

    public CarRussia(Long carId,
                     String brand,
                     String model,
                     String plateNumber,
                     Integer capacity,
                     Boolean available,
                     String driverName,
                     String driverPhone) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.plateNumber = plateNumber;
        this.capacity = capacity;
        this.available = available;
        this.driverName = driverName;
        this.driverPhone = driverPhone;
    }

    public CarRussia(String brand,
                     String model,
                     String plateNumber,
                     Integer capacity,
                     Boolean available,
                     String driverName,
                     String driverPhone) {
        this.brand = brand;
        this.model = model;
        this.plateNumber = plateNumber;
        this.capacity = capacity;
        this.available = available;
        this.driverName = driverName;
        this.driverPhone = driverPhone;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }
}

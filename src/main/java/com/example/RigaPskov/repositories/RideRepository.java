package com.example.RigaPskov.repositories;

import com.example.RigaPskov.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.EntityGraph;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    Ride findFirstByDateAndTimeAndDirectionAndSeatsLeftGreaterThan(
            Date date,
            Time time,
            String direction,
            Integer seats);
    Ride findFirstByDateAndTimeAndDirection(
            Date date,
            Time time,
            String direction);

    @Override
    @EntityGraph(attributePaths = {"carLatvia", "carRussia"})
    List<Ride> findAll();

}

package com.example.RigaPskov.repositories;

import com.example.RigaPskov.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query("SELECT r FROM Request r WHERE r.ride IS NULL")
    List<Request> findAllUnprocessed();
}

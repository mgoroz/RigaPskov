package com.example.RigaPskov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.RigaPskov.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

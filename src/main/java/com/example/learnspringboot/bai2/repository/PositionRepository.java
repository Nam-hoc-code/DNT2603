package com.example.learnspringboot.bai2.repository;

import com.example.learnspringboot.bai2.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Integer> {
    boolean existsByName(String name);
}
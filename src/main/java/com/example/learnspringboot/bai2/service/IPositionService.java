package com.example.learnspringboot.bai2.service;

import com.example.learnspringboot.bai2.entity.Position;

import java.util.List;
import java.util.Optional;

public interface IPositionService {
    List<Position> getAll();
    Optional<Position> getById(Integer id);
    Position create(Position position);
    Position update(Integer id, Position updated);
    void delete(Integer id);
}
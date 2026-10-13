package com.example.learnspringboot.bai2.service;

import com.example.learnspringboot.bai2.entity.Position;
import com.example.learnspringboot.bai2.repository.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {
    private final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public List<Position> getAll() {
        return positionRepository.findAll();
    }

    public Optional<Position> getById(Integer id) {
        return positionRepository.findById(id);
    }

    public Position create(Position position) {
        return positionRepository.save(position);
    }

    public Position update(Integer id, Position updated) {
        Position pos = positionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Position not found: " + id));
        pos.setName(updated.getName());
        pos.setDescription(updated.getDescription());
        return positionRepository.save(pos);
    }

    public void delete(Integer id) {
        positionRepository.deleteById(id);
    }
}
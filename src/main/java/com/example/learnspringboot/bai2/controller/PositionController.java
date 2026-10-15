package com.example.learnspringboot.bai2.controller;

import com.example.learnspringboot.bai2.entity.Position;
import com.example.learnspringboot.bai2.service.IPositionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
public class PositionController {
    private final IPositionService positionService;

    public PositionController(IPositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping
    public List<Position> getAll() {
        return positionService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> getById(@PathVariable Integer id) {
        return positionService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Position create(@RequestBody Position position) {
        return positionService.create(position);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Position> update(@PathVariable Integer id, @RequestBody Position position) {
        try {
            return ResponseEntity.ok(positionService.update(id, position));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (positionService.getById(id).isPresent()) {
            positionService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
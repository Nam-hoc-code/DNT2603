package com.example.learnspringboot.bai2.controller;

import com.example.learnspringboot.bai2.entity.Department;
import com.example.learnspringboot.bai2.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Department> getAll() {
        return departmentService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable Integer id) {
        return departmentService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Department create(@RequestBody Department department) {
        return departmentService.create(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> update(@PathVariable Integer id, @RequestBody Department department) {
        try {
            return ResponseEntity.ok(departmentService.update(id, department));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (departmentService.getById(id).isPresent()) {
            departmentService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
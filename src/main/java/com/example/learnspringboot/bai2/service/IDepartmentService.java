package com.example.learnspringboot.bai2.service;

import com.example.learnspringboot.bai2.entity.Department;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService {
    List<Department> getAll();
    Optional<Department> getById(Integer id);
    Department create(Department department);
    Department update(Integer id, Department updated);
    void delete(Integer id);
}
package com.example.learnspringboot.bai2.repository;

import com.example.learnspringboot.bai2.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    boolean existsByName(String name);
}
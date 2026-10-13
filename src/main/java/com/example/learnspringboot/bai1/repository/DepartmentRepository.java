package com.example.learnspringboot.bai1.repository;

import com.example.learnspringboot.bai1.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
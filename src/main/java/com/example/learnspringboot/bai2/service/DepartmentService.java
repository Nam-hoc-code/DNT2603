package com.example.learnspringboot.bai2.service;

import com.example.learnspringboot.bai2.entity.Department;
import com.example.learnspringboot.bai2.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getById(Integer id) {
        return departmentRepository.findById(id);
    }

    public Department create(Department department) {
        return departmentRepository.save(department);
    }

    public Department update(Integer id, Department updated) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found: " + id));
        dept.setName(updated.getName());
        dept.setDescription(updated.getDescription());
        return departmentRepository.save(dept);
    }

    public void delete(Integer id) {
        departmentRepository.deleteById(id);
    }
}
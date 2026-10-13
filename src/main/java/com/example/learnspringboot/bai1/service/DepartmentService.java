package com.example.learnspringboot.bai1.service;

import com.example.learnspringboot.bai1.entity.Department;
import com.example.learnspringboot.bai1.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Integer id) {
        return departmentRepository.findById(id);
    }

    public Department updateDepartment(Integer id, Department departmentDetails) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
        department.setName(departmentDetails.getName());
        department.setNumberMember(departmentDetails.getNumberMember());
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Integer id) {
        departmentRepository.deleteById(id);
    }
}
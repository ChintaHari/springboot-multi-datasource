package com.example.springboot_multi_datasource.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot_multi_datasource.entity.department.Department;
import com.example.springboot_multi_datasource.repository.department.DepartmentRepository;

@Service
public class DepartmentService {
    
    @Autowired
    public DepartmentRepository departmentRepository;

    // public DepartmentService(DepartmentRepository departmentRepository) {
    //     List<Department> departments = List.of(
    //         new Department(0, "HR", "Chicago"),
    //         new Department(1, "IT", "New York"));
        
    //         departmentRepository.saveAll(departments);
    // }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Optional<Department> getDepartmentById(int id) {
        return departmentRepository.findById(id);
    }

    public Optional<Department> getDepartmentByName(String name) {
        return departmentRepository.findByName(name);
    }
}

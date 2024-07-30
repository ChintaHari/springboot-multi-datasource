package com.example.springboot_multi_datasource.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot_multi_datasource.entity.department.Department;
import com.example.springboot_multi_datasource.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    
    @Autowired
    public DepartmentService departmentService;

    @GetMapping("/all")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PostMapping("/add")
    public Department addDepartment(@RequestBody Department department) {
        return departmentService.addDepartment(department);
    }
    
    @GetMapping("/name/{name}") 
    public Optional<Department> getDepartmentByName(@PathVariable String name) {
        return departmentService.getDepartmentByName(name);
    }

    @GetMapping("/id/{id}")
    public Optional<Department> getDepartmentById(@PathVariable int id) {
        return departmentService.getDepartmentById(id);
    }

}

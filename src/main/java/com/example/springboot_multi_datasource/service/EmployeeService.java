package com.example.springboot_multi_datasource.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot_multi_datasource.entity.employee.Employee;
import com.example.springboot_multi_datasource.repository.employee.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // public EmployeeService(EmployeeRepository employeeRepository) {
    //     List<Employee> employees = List.of(
    //         new Employee(0, "John Doe", "johndoe@gmail.com", "HR"),
    //         new Employee(1, "Jane Doe", "janedoe@gmail.com", "IT"),
    //         new Employee(2, "Alice", "alice@gmail.com", "HR"),
    //         new Employee(3, "Bob", "bob@gmail.com", "IT"));

    //     employeeRepository.saveAll(employees);

    // }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    
    public Optional<Employee> getEmployeeByName(String name) {
        return employeeRepository.findByName(name);
    }
}

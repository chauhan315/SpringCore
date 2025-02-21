package com.example.employeetaskmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeetaskmanager.model.Employee;
import com.example.employeetaskmanager.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository empRepo;

    @Override
    public void addEmployee(Employee employee) {
        empRepo.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return empRepo.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(int id) {
        return empRepo.findById(id);
    }

    @Override
    public void deleteEmployee(int id) {
        if (empRepo.existsById(id)) {
            empRepo.deleteById(id);
        } else {
            throw new RuntimeException("Employee with ID " + id + " not found.");
        }
    }
}

package com.example.employeetaskmanager.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.employeetaskmanager.model.Employee;
import com.example.employeetaskmanager.model.Task;
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
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
    	List<Employee> employees = empRepo.findAll();

        for (Employee employee : employees) {
            if (employee.getTasks() != null) {
                Hibernate.initialize(employee.getTasks());
            }
        }
        return employees;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> getEmployeeById(int id) {
        return empRepo.findById(id);
    }

    @Override
    @Transactional
    public void deleteEmployee(int id) {
        if (empRepo.existsById(id)) {
            empRepo.deleteById(id);
        } else {
            throw new RuntimeException("Employee with ID " + id + " not found.");
        }
    }

	@Override
	@Transactional(readOnly = true)
	public Optional<Employee> getEmployeeByEmail(String email) {
		return empRepo.findEmployeeByEmail(email);
	}
}

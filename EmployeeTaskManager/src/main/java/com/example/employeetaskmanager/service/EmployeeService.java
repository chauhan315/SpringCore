package com.example.employeetaskmanager.service;

import java.util.List;
import java.util.Optional;

import com.example.employeetaskmanager.model.Employee;

public interface EmployeeService {

	void addEmployee(Employee employee);

	List<Employee> getAllEmployees();

	Optional<Employee> getEmployeeById(int id);

	void deleteEmployee(int id);
}

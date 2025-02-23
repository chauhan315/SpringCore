package com.example.employeetaskmanager.runner;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.employeetaskmanager.model.Employee;
import com.example.employeetaskmanager.model.Role;
import com.example.employeetaskmanager.service.EmployeeService;
import com.example.employeetaskmanager.utils.Menus;

@Component
public class AppRunner implements CommandLineRunner {

	@Autowired
	private Scanner sc;

	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private Menus menu;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("=== Welcome to Employee Task Manager ===");
		loginUser();
	}

	private void loginUser() {
		System.out.println("Enter your email...");
		String email = sc.nextLine();
		
		System.out.println("Enter your password...");
		String password = sc.nextLine();
		
		Optional<Employee> employeeOpt = empService.getEmployeeByEmail(email);
		
		if(employeeOpt.isPresent()) {
			Employee employee = employeeOpt.get();
			
			if(employee.getPassword().equals(password)) {
				System.out.println("Welcome, " + employee.getName() + "!");
				
				if(employee.getRole().equals(Role.ADMIN)) {
					menu.adminMenu();
				} else {
					menu.employeeMenu(employee.getEmployee_id());
				}
			} else {
				System.out.println("Incorrect Password!... try again");
				loginUser();
			}
		} else {
			System.out.println("No user with the email present... check again...");
			loginUser();
		}
		
	}
}

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
    private Scanner scanner;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private Menus menu;

    @Override
    public void run(String... args) {
        System.out.println("=== Welcome to Employee Task Manager ===");
        loginUser();
    }

    private void loginUser() {
        while (true) {
            System.out.println("\nEnter your email:");
            String email = scanner.nextLine().trim();

            System.out.println("Enter your password:");
            String password = scanner.nextLine().trim();

            Optional<Employee> employeeOpt = employeeService.getEmployeeByEmail(email);

            if (employeeOpt.isPresent()) {
                Employee employee = employeeOpt.get();

                if (employee.getPassword().equals(password)) {
                    System.out.println("\nWelcome, " + employee.getName() + "!");

                    if (employee.getRole() == Role.ADMIN) {
                        menu.adminMenu();
                    } else {
                        menu.employeeMenu(employee.getEmployee_id());
                    }
                    return; 
                } else {
                    System.out.println("\n❌ Incorrect Password! Please try again.");
                }
            } else {
                System.out.println("\n❌ No user found with this email. Please check again.");
            }
        }
    }
}

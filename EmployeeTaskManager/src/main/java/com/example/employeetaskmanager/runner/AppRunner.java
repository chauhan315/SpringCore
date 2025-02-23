package com.example.employeetaskmanager.runner;

import com.example.employeetaskmanager.aspect.SecurityAspect;
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
        while (true) {
            loginUser();
        }
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
                    System.out.println("\n✅ Welcome, " + employee.getName() + "!");
                    
                    SecurityAspect.setCurrentUser(employee);

                    boolean logout = false;
                    while (!logout) {
                        if (employee.getRole() == Role.ADMIN) {
                            logout = adminMenu(); 
                        } else {
                            logout = employeeMenu(employee.getEmployeeId());
                        }
                    }

                    SecurityAspect.clearCurrentUser();
                    System.out.println("\n🔄 You have logged out. Returning to login...\n");
                } else {
                    System.out.println("\n❌ Incorrect Password! Please try again.");
                }
            } else {
                System.out.println("\n❌ No user found with this email. Please check again.");
            }
        }
    }

    private boolean adminMenu() {
        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Perform Admin Actions");
            System.out.println("2. Logout");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    menu.adminMenu();
                    break;
                case "2":
                    return true;
                default:
                    System.out.println("\n❌ Invalid choice! Try again.");
            }
        }
    }

    private boolean employeeMenu(int employeeId) {
        while (true) {
            System.out.println("\n=== Employee Menu ===");
            System.out.println("1. Perform Employee Actions");
            System.out.println("2. Logout");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    menu.employeeMenu(employeeId);
                    break;
                case "2":
                    return true;
                default:
                    System.out.println("\n❌ Invalid choice! Try again.");
            }
        }
    }
}

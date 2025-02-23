package com.example.employeetaskmanager.utils;

import java.util.Optional;
import java.util.Scanner;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.employeetaskmanager.model.Employee;
import com.example.employeetaskmanager.model.Role;
import com.example.employeetaskmanager.model.Task;
import com.example.employeetaskmanager.model.TaskPriority;
import com.example.employeetaskmanager.model.TaskStatus;
import com.example.employeetaskmanager.service.EmployeeService;
import com.example.employeetaskmanager.service.TaskService;

@Component
public class Menus {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private Scanner scanner;
    
    @Autowired
    private Employee employee;
    
    @Autowired
    private Task task;

    public void adminMenu() {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Assign Task");
            System.out.println("3. View All Employees");
            System.out.println("4. View All Tasks");
            System.out.println("5. Delete Employee");
            System.out.println("6. View an Employee By Id");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    assignTask();
                    break;
                case 3:
                    viewAllEmployees();
                    break;
                case 4:
                    viewAllTasks();
                    break;
                case 5:
                    deleteEmployee();
                    break;
                case 6:
                    viewEmployeeById();
                    break;
                case 7:
                	System.out.println("Exiting Admin Menu...");
                	return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public void employeeMenu(int employeeId) {
        while (true) {
            System.out.println("\n--- Employee Menu ---");
            System.out.println("1. View My Tasks");
            System.out.println("2. Update Task Status");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewMyTasks(employeeId);
                    break;
                case 2:
                    updateTaskStatus(employeeId);
                    break;
                case 3:
                    System.out.println("Exiting Employee Menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void addEmployee() {
        System.out.println("Adding Employee...");
        System.out.println("Enter the Employee Details separated by commas in the given order...");
        System.out.println("name,email,password");
        String data = scanner.nextLine();
        
        System.out.println("Choose role: 1->Admin, 2->Employee");
        int roleChoice = scanner.nextInt();
        scanner.nextLine(); 

        Role role = (roleChoice == 2) ? Role.EMPLOYEE : Role.ADMIN;
        
        String[] dataArray = data.split(",");
        
        employee.setName(dataArray[0]);
        employee.setEmail(dataArray[1]);
        employee.setPassword(dataArray[2]);
        employee.setRole(role);
        
        employeeService.addEmployee(employee);
        System.out.println("Employee added successfully.");
    }

    private void assignTask() {
        System.out.println("Assigning Task...");
        System.out.print("Enter the Employee ID: ");
        int employee_Id = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Enter the Task Details separated by commas in the given order...");
        System.out.println("title,description");
        String data = scanner.nextLine();
        String[] dataArray = data.split(",");

        System.out.println("Select Task Priority...");
        System.out.println("1->Low, 2->Medium, 3->High, 4->Urgent");
        int taskPriorityChoice = scanner.nextInt();
        scanner.nextLine();

        TaskPriority taskPriority = switch (taskPriorityChoice) {
            case 2 -> TaskPriority.MEDIUM;
            case 3 -> TaskPriority.HIGH;
            case 4 -> TaskPriority.URGENT;
            default -> TaskPriority.LOW;
        };

       
        Optional<Employee> optionalEmployee = employeeService.getEmployeeById(employee_Id);
        if (optionalEmployee.isEmpty()) {
            System.out.println("Employee with ID " + employee_Id + " not found.");
            return;
        }

        employee = optionalEmployee.get();
        task.setTitle(dataArray[0]);
        task.setDescription(dataArray[1]);
        task.setPriority(taskPriority);
        task.setStatus(TaskStatus.PENDING);

        taskService.assignTask(task, employee_Id);
        System.out.println("Task assigned successfully.");
    }

    private void viewAllEmployees() {
        System.out.println("Displaying all Employees...");
        for (Employee emp : employeeService.getAllEmployees()) {
            System.out.println(emp);
        }
    }

    private void viewAllTasks() {
        System.out.println("Displaying all Tasks...");
        for (Task t : taskService.getAllTasks()) {
            System.out.println(t);
        }
    }

    private void deleteEmployee() {
        System.out.print("Enter Employee ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        Optional<Employee> optionalEmployee = employeeService.getEmployeeById(id);
        if (optionalEmployee.isEmpty()) {
            System.out.println("Employee not found.");
            return;
        }

        employeeService.deleteEmployee(id);
        System.out.println("Employee deleted successfully.");
    }

    private void viewEmployeeById() {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        Employee employee;
        
        Optional<Employee> optionalEmployee = employeeService.getEmployeeById(id);
        if (optionalEmployee.isPresent()) {
        	employee = optionalEmployee.get();
        	Hibernate.initialize(employee.getTasks());
            System.out.println(employee);
        } else {
            System.out.println("Employee not found.");
        }
    }

    private void viewMyTasks(int employeeId) {
        System.out.println("Fetching tasks for Employee ID: " + employeeId);
        for (Task task : taskService.getTasksByEmployee(employeeId)) {
            System.out.println(task);
        }
    }

    private void updateTaskStatus(int employeeId) {
        System.out.print("Enter Task ID to update: ");
        int taskId = scanner.nextInt();
        scanner.nextLine(); 
        
        System.out.println("Select Task Status: 1->Pending, 2->In Progress, 3->Completed");
        int statusChoice = scanner.nextInt();
        scanner.nextLine(); 

        TaskStatus status = switch (statusChoice) {
            case 2 -> TaskStatus.IN_PROGRESS;
            case 3 -> TaskStatus.COMPLETED;
            default -> TaskStatus.PENDING;
        };

        taskService.updateTaskStatus(taskId, status);
        System.out.println("Task status updated.");
    }
}

package com.example.employeetaskmanager.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.employeetaskmanager.model.Employee;
import com.example.employeetaskmanager.model.Task;
import com.example.employeetaskmanager.model.TaskStatus;
import com.example.employeetaskmanager.repository.EmployeeRepository;
import com.example.employeetaskmanager.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {
    
    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private EmployeeRepository empRepo; 

    @Override
    public void assignTask(Task task, int employeeId) {
        Optional<Employee> employeeOpt = empRepo.findById(employeeId);
        
        if (employeeOpt.isPresent()) {
            task.setAssignedTo(employeeOpt.get()); 
            taskRepo.save(task);
        } else {
            throw new RuntimeException("Employee with ID " + employeeId + " not found.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllTasks() {
    	 List<Task> tasks = taskRepo.findAll();

         for (Task task : tasks) {
             if (task.getAssignedTo() != null) {
                 Hibernate.initialize(task.getAssignedTo().getName());
             }
         }
         return tasks;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getTasksByEmployee(int employeeId) {
        Optional<Employee> employeeOpt = empRepo.findById(employeeId);
        if (employeeOpt.isPresent()) {
            return taskRepo.findByAssignedTo(employeeOpt.get());  // Calls the automatically generated query
        } else {
            throw new RuntimeException("Employee with ID " + employeeId + " not found.");
        }
    }

    @Override
    public void updateTaskStatus(int taskId, TaskStatus status) {
        Optional<Task> taskOpt = taskRepo.findById(taskId);
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            task.setStatus(status);
            taskRepo.save(task);
        } else {
            throw new RuntimeException("Task with ID " + taskId + " not found.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void deleteTask(int taskId) {
        if (taskRepo.existsById(taskId)) {
            taskRepo.deleteById(taskId);
        } else {
            throw new RuntimeException("Task with ID " + taskId + " not found.");
        }
    }

}

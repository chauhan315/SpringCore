package com.example.employeetaskmanager.service;

import java.util.List;

import com.example.employeetaskmanager.model.Task;
import com.example.employeetaskmanager.model.TaskStatus;

public interface TaskService {

	void assignTask(Task task, int employeeId);

	List<Task> getAllTasks();

	List<Task> getTasksByEmployee(int employeeId);

	void updateTaskStatus(int taskId, TaskStatus status);

	void deleteTask(int taskId);
}

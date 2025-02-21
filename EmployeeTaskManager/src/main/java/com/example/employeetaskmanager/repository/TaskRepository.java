package com.example.employeetaskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employeetaskmanager.model.Employee;
import com.example.employeetaskmanager.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{
	
	List<Task> findByAssignedTo(Employee assignedTo);
	
//	@Query("SELECT t FROM Task t WHERE t.assignedTo = :employee")
//	List<Task> findTasksByEmployee(@Param("employee") Employee employee);
}

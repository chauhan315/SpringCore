package com.example.employeetaskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employeetaskmanager.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{

}

package com.example.employeetaskmanager.config;

import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.example.employeetaskmanager.model.Employee;
import com.example.employeetaskmanager.model.Task;

@Configuration
public class AppConfig {
	
	@Bean
	@Scope("prototype")
	public Employee employee() {
		return new Employee(); 
	}

	@Bean
	@Scope("prototype")
	public Task task() {
		return new Task(); 
	}
	
	@Bean
	public Scanner scanner() {
		return new Scanner(System.in);
	}
}

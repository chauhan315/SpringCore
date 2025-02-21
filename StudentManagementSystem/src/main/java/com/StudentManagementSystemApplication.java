package com;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.example.student.entity.Student;
import com.example.student.repository.StudentRepository;

@SpringBootApplication
public class StudentManagementSystemApplication {
	
	

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
	}
}


@Component
class KeepApplicationRunning implements CommandLineRunner {
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Application is running... Press Ctrl+C to stop.");

		List<Student> students = studentRepository.findAll();

		// Check if students exist
		if (students.isEmpty()) {
			System.out.println("No students found.");
		} else {
			System.out.println("List of Students:");
			students.forEach(System.out::println);
		}
	}
}

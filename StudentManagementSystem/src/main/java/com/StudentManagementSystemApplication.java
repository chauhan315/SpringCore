package com;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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
	
	
	Scanner scanner = new Scanner(System.in);
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Application is running... Press Ctrl+C to stop.");
		System.out.println("---------------------------------------------------------------");

		while(true) {
			System.out.println("Select your option:\n"+
					"1. View all the students\n"+
					"2. Add a new student\n"+
					"3. Delete a student\n"+
					"4. Exit the program"
					);
			
			 int choice = scanner.nextInt();
             scanner.nextLine(); 

             switch (choice) {
                 case 1:
                     List<Student> students = studentRepository.findAll();
                     if (students.isEmpty()) {
                         System.out.println("No students found.");
                     } else {
                         students.forEach(System.out::println);
                     }
                     break;

                 case 2:
                     System.out.print("Enter student name: ");
                     String name = scanner.nextLine();
                     
                     System.out.print("Enter email: ");
                     String email = scanner.nextLine();
                     
                     System.out.print("Enter age: ");
                     int age = scanner.nextInt();
                     scanner.nextLine(); 
                     
                     System.out.print("Enter course: ");
                     String course = scanner.nextLine();
                     
                     Student newStudent = new Student(null, name, email, age, course);
                     studentRepository.save(newStudent);
                     System.out.println("Student added successfully!");
                     break;

                 case 3:
                     System.out.print("Enter student ID to delete: ");
                     long id = scanner.nextLong();
                     scanner.nextLine(); 
                     
                     Optional<Student> studentToDelete = studentRepository.findById(id);
                     if (studentToDelete.isPresent()) {
                         studentRepository.deleteById(id);
                         System.out.println("Student deleted successfully!");
                     } else {
                         System.out.println("Student not found.");
                     }
                     break;

                 case 4:
                     System.out.println("Exiting program...");
                     scanner.close();
                     return; 

                 default:
                     System.out.println("Invalid choice! Please try again.");
                     break;
             }
		}
	}
}

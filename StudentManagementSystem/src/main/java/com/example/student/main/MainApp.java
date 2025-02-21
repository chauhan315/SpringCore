package com.example.student.main;

import com.example.student.entity.Student;
import com.example.student.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication(scanBasePackages = "com.example.student") // Ensures all beans are scanned
public class MainApp {
    public static void main(String[] args) {
        // Use Spring Boot's ApplicationContext
        ApplicationContext context = SpringApplication.run(MainApp.class, args);
        StudentService studentService = context.getBean(StudentService.class);

        // Create new students
        Student student1 = new Student();
        student1.setName("Alice");
        student1.setEmail("alice@example.com");
        student1.setAge(20);
        student1.setCourse("Computer Science");

        Student student2 = new Student();
        student2.setName("Bob");
        student2.setEmail("bob@example.com");
        student2.setAge(22);
        student2.setCourse("Mathematics");


        // Save students
        studentService.saveStudent(student1);
        studentService.saveStudent(student2);

        // Fetch and display all students
        List<Student> students = studentService.getAllStudents();
        System.out.println("All Students:");
        students.forEach(System.out::println);

        // Fetch and update a student
        Student student = studentService.getStudentById(1L);
        if (student != null) {
            System.out.println("Fetched Student: " + student);
            student.setCourse("Physics");
            studentService.updateStudent(student);
        }

        // Delete a student
        studentService.deleteStudent(2L);
    }
}

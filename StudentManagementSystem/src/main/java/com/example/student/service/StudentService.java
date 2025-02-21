package com.example.student.service;

import com.example.student.entity.Student;

import java.util.List;

public interface StudentService {
    void saveStudent(Student student);
    Student getStudentById(Long id);
    List<Student> getAllStudents();
    void updateStudent(Student student);
    void deleteStudent(Long id);
}

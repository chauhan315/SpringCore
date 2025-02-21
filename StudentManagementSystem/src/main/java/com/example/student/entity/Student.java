package com.example.student.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private int age;

	@Column(nullable = false)
	private String course;

	public Student() {
		super();
	}

	public Student(Long id, String name, String email, int age, String course) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.age = age;
		this.course = course;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Student{id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + ", age=" + age
				+ ", course='" + course + '\'' + '}';
	}
}

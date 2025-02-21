package com.example.employeetaskmanager.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee", schema = "mentor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id", nullable = false)
	private int employee_id;

	@Column(name = "employee_name", nullable = false)
	private String name;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Role role;

	@OneToMany(mappedBy = "assignedTo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Task> tasks;
}

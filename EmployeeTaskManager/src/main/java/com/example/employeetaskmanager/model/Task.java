package com.example.employeetaskmanager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "task", schema = "mentor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "task_id", nullable = false, unique = true)
	private int task_id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private TaskStatus status;

	@ManyToOne
	@JoinColumn(name = "assigned_to", nullable = false)
	private Employee assignedTo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "priority")
	private TaskPriority priority;
}

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

	public Task(int task_id, String title, String description, TaskStatus status, Employee assignedTo,
			TaskPriority priority) {
		super();
		this.task_id = task_id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.assignedTo = assignedTo;
		this.priority = priority;
	}

	public Task() {
		super();
	}

	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public Employee getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(Employee assignedTo) {
		this.assignedTo = assignedTo;
	}

	public TaskPriority getPriority() {
		return priority;
	}

	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "Task [task_id=" + task_id + ", title=" + title + ", description=" + description + ", status=" + status
				+ ", assignedTo=" + assignedTo + ", priority=" + priority + "]";
	}

}

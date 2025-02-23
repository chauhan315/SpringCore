package com.example.employeetaskmanager.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "task", schema = "mentor")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", nullable = false, unique = true)
    private int taskId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to", nullable = false)
    private Employee assignedTo;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private TaskPriority priority;

    public Task() {
    }

    public Task(int taskId, String title, String description, TaskStatus status, Employee assignedTo, TaskPriority priority) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.assignedTo = assignedTo;
        this.priority = priority;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
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
        return "Task{" +
                "taskId=" + taskId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", assignedTo=" + (assignedTo != null ? assignedTo.getName() : "None") +
                ", priority=" + priority +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return taskId == task.taskId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId);
    }
}

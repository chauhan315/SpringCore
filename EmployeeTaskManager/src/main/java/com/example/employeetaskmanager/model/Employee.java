package com.example.employeetaskmanager.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "employee", schema = "mentor")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private int employeeId;

    @Column(name = "employee_name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToMany(mappedBy = "assignedTo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Task> tasks;

    public Employee() {
    }

    public Employee(int employeeId, String name, String email, String password, Role role, List<Task> tasks) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.tasks = tasks;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", tasks=" + (tasks != null ? tasks.size() + " tasks" : "No tasks") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId && Objects.equals(email, employee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, email);
    }
}

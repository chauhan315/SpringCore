# Employee Task Manager

A **Spring Boot** standalone **command-line** application that helps manage employee tasks while using a **MySQL database**. The application can be run **locally** or inside a **Docker container** with **dynamic user input** for database configuration.

---

## Architecture

This is a standalone **Spring Boot CLI application** that interacts with a **MySQL database**. It can run:
1. **Directly on the host machine (Windows, Linux, Mac)**
2. **Inside a Docker container**, where it checks if MySQL is running **inside Docker** or on the **host machine**.

              ┌──────────────────────┐
              │  Employee Task CLI   │
              │ (Spring Boot + JDBC) │
              └──────────┬───────────┘
                         │
              ┌──────────▼──────────┐
              │ MySQL Database      │
              │ (Localhost / Docker)│
              └─────────────────────┘


---

## Technology Stack

- **Java 17**
- **Spring Boot**
- **JDBC**
- **MySQL**
- **Docker**
- **Maven**

---

## Features

✔ **Standalone CLI application**  
✔ **User inputs MySQL schema, username, and password at runtime**  
✔ **Works both locally and inside Docker**  
✔ **Detects if MySQL is running inside Docker or on localhost**  
✔ **Fully containerized with Docker**  

---

## Setup and Running Locally

### 1. Clone the Repository

git clone https://github.com/chauhan315/SpringCore.git
cd EmployeeTaskManager

### 2. Build the JAR File Using Maven

Build the JAR File Using Maven

### 3. Run the Application Locally

java -jar target/EmployeeTaskManager-0.0.1-SNAPSHOT.jar

### 4. Follow the Prompts:

Enter MySQL database schema <br>
Enter MySQL username <br>
Enter MySQL password <br> 

<hr>

##Docker Instructions for Running the App

### 1. Pull the Docker Image
docker pull chauhan315/employee-task-manager:v1

### 2. Run the Application with Your MySQL Server
docker run -it -e dockerEnv=true employee-task-manager:v1

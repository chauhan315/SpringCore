# Student Management System

## Project Overview
The **Student Management System** is a Spring Boot-based CLI application that allows an admin to manage students. The admin can view all students, add new students, and delete students. The application uses Spring Boot, Spring Data JPA, Hibernate, and MySQL as the database.

## Project Structure
The project follows a typical Spring Boot structure:

```
StudentManagementSystem/
│-- src/main/java/com/example/student/
│   ├── entity/       # JPA Entity classes
│   ├── repository/   # Repository layer for database interaction
│   ├── service/      # Business logic and service layer
│   ├── config/       # Configuration classes (DataSourceConfig)
│   ├── runner/       # CommandLineRunner implementation
│   ├── main/         # Main entry point (Spring Boot Application)
│-- src/main/resources/
│   ├── application.properties  # Configuration properties
│-- pom.xml  # Maven dependencies
```

## Dependencies
The project uses the following dependencies:

- **Spring Boot Starter Web**: For building the application
- **Spring Boot Starter Data JPA**: For interacting with MySQL database
- **MySQL Connector**: For connecting to MySQL
- **Lombok**: To reduce boilerplate code (annotations like `@Getter`, `@Setter`, etc.)
- **Spring Boot DevTools**: For development enhancements

## Configuration

In `application.properties`, the following settings are configured:

```
spring.datasource.url=jdbc:mysql://localhost:3306/student_db
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

To make database configurations dynamic, a `DataSourceConfig` class is created to accept user input at runtime.

## How the Application Works
1. The application starts with `SpringBootApplication`.
2. The `CommandLineRunner` prompts the user to choose an action (View, Add, Delete students).
3. The selected action is performed using the `StudentService`, which interacts with `StudentRepository`.
4. Student data is stored and managed in a MySQL database.
5. Hibernate handles ORM mappings automatically.

## How to Build and Run the Application

### Using Maven
1. **Build the project**:
   ```sh
   mvn clean package
   ```
2. **Run the JAR file**:
   ```sh
   java -jar target/StudentManagementSystem-1.0.jar
   ```

### Running from Eclipse/IntelliJ
- Run `StudentManagementSystemApplication` as a **Spring Boot Application**.

## Future Enhancements
- Add more student attributes (e.g., phone number, address)
- Implement authentication and role-based access control
- Improve user interaction with a GUI (JavaFX or Swing)

---

This README provides an overview of the project, its structure, dependencies, and how to run it. Let me know if any modifications are needed!

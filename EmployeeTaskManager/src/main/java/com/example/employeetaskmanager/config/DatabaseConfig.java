package com.example.employeetaskmanager.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("[INFO] Please first create a schema in your MySQL...");
        System.out.print("Enter MySQL Database Schema: ");
        String schema = scanner.nextLine();

        System.out.print("Enter MySQL Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter MySQL Password: ");
        String password = scanner.nextLine();

        // Check if running inside Docker
        String dockerEnv = System.getenv("DOCKER_ENV");
        boolean runningInsideDocker = (dockerEnv != null && dockerEnv.equals("true"));

        // Ask for MySQL container name if running inside Docker
        String mysqlContainerName = "localhost"; // Default to local MySQL
        if (runningInsideDocker) {
            System.out.print("[INFO} Enter MySQL Container Name (if using Docker): ");
            mysqlContainerName = scanner.nextLine().trim();
            if (mysqlContainerName.isEmpty()) {
                mysqlContainerName = "host.docker.internal"; // Default fallback
            }
        }

        String host = testMySQLConnection(mysqlContainerName, schema, username, password)
                ? mysqlContainerName
                : "localhost";

        System.out.println("Running inside Docker: " + runningInsideDocker);
        System.out.println("Using MySQL host: " + host);

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://" + host + ":3306/" + schema);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    private boolean testMySQLConnection(String host, String schema, String username, String password) {
        String url = "jdbc:mysql://" + host + ":3306/" + schema;
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("[✔] Successfully connected to MySQL at " + host);
            return true;
        } catch (SQLException e) {
            System.out.println("[✘] Failed to connect to MySQL at " + host);
            return false;
        }
    }
}

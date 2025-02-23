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
    public DataSource dataSource(Scanner scanner) {
        String schema = null;
        String username = null;
        String password = null;
        boolean authenticated = false;

        while (!authenticated) {
            System.out.println("[+Note] Please first create a schema in your MySQL...");
            System.out.print("Enter MySQL Database Schema: ");
            schema = scanner.nextLine();

            System.out.print("Enter MySQL Username: ");
            username = scanner.nextLine();

            System.out.print("Enter MySQL Password: ");
            password = scanner.nextLine();

            if (validateCredentials(schema, username, password)) {
                authenticated = true;
                System.out.println("[✔] Authentication Successful.");
            } else {
                System.out.println("[✘] Authentication Failed! Please try again.");
            }
        }

        String dockerEnv = System.getenv("DOCKER_ENV");
        String host = (dockerEnv != null && dockerEnv.equals("true")) ? "host.docker.internal" : "localhost";

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://" + host + ":3306/" + schema);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    private boolean validateCredentials(String schema, String username, String password) {
        String url = "jdbc:mysql://localhost:3306/" + schema;
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            return true; 
        } catch (SQLException e) {
            return false; 
        }
    }
}

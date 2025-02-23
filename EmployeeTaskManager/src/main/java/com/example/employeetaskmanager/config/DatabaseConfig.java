package com.example.employeetaskmanager.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        String dockerEnv = System.getenv("DOCKER_ENV");
        boolean runningInsideDocker = (dockerEnv != null && dockerEnv.equals("true"));

        
        String dockerMySQLHost = "mysql-container"; 
        String localMySQLHost = "localhost";
        String defaultMySQLHost = runningInsideDocker ? "host.docker.internal" : localMySQLHost;

        String schema = "mentor";  
        String username = "root";  
        String password = "yourpassword";  

        String host = testMySQLConnection(dockerMySQLHost, schema, username, password)
                ? dockerMySQLHost
                : defaultMySQLHost;

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

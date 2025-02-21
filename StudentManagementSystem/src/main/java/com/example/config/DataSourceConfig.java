package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Scanner;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter MySQL Username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter MySQL Password: ");
        String password = scanner.nextLine();
        
        System.out.print("Enter Database Name: ");
        String database = scanner.nextLine();
        
        String url = "jdbc:mysql://localhost:3306/" + database + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        
        
        return dataSource;
    }
}

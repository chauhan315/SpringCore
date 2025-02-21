package com.example.employeetaskmanager.config;

import java.util.Scanner;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfig {

	@Bean
	public Scanner scanner() {
		return new Scanner(System.in);
	}

	@Bean
	public DataSource dataSource(Scanner scanner) {
		System.out.print("Enter MySQL Database Schema: ");
		String schema = scanner.nextLine();

		System.out.print("Enter MySQL Username: ");
		String username = scanner.nextLine();

		System.out.print("Enter MySQL Password: ");
		String password = scanner.nextLine();

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/" + schema);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		return dataSource;

	}
}

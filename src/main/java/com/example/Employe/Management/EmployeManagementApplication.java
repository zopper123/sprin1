package com.example.Employe.Management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:1111")  
public class EmployeManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeManagementApplication.class, args);
    }
}
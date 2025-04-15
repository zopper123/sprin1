package com.example.Employe.Management.Controller;

import lombok.Data;

@Data
public class Employee {
    private Long id; // Add id field for identification
    private String name;
    private String phone;
    private String email;
}

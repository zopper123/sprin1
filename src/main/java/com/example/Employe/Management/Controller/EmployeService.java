package com.example.Employe.Management.Controller;

import java.util.List;

public interface EmployeService {

    // Method to create a new employee
    EmployeeEntity createEmployee(EmployeeEntity employee);

    // Method to retrieve a list of all employees
    List<EmployeeEntity> readEmployees();

    // Method to delete an employee by ID
    String deleteEmployee(Long id);

    // Method to update an existing employee (Full Update)
    EmployeeEntity updateEmployee(Long id, EmployeeEntity updatedEmployee);

    // Method to partially update an existing employee
    EmployeeEntity partialUpdateEmployee(Long id, EmployeeEntity updatedEmployee);
}

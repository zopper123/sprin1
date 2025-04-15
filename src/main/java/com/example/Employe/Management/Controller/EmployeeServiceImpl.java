package com.example.Employe.Management.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Employe.Management.Repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeEntity createEmployee(EmployeeEntity employee) {
        return employeeRepository.save(employee); // Saving the employee entity
    }

    @Override
    public List<EmployeeEntity> readEmployees() {
        return employeeRepository.findAll(); // Return all employees from the database
    }

    @Override
    public String deleteEmployee(Long id) {
        Optional<EmployeeEntity> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.delete(employee.get()); // Delete the employee from the database
            return "Deleted successfully";
        } else {
            return "Employee not found"; // If employee is not found
        }
    }

    @Override
    public EmployeeEntity updateEmployee(Long id, EmployeeEntity updatedEmployee) {
        Optional<EmployeeEntity> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isPresent()) {
            updatedEmployee.setId(id); // Ensure the ID is not overwritten
            return employeeRepository.save(updatedEmployee); // Save the updated employee
        } else {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found"); // Handle employee not found
        }
    }

    @Override
    public EmployeeEntity partialUpdateEmployee(Long id, EmployeeEntity updatedEmployee) {
        Optional<EmployeeEntity> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            EmployeeEntity existingEmployee = employee.get();
            if (updatedEmployee.getName() != null) {
                existingEmployee.setName(updatedEmployee.getName()); // Update name if provided
            }
            if (updatedEmployee.getPhone() != null) {
                existingEmployee.setPhone(updatedEmployee.getPhone()); // Update phone if provided
            }
            if (updatedEmployee.getEmail() != null) {
                existingEmployee.setEmail(updatedEmployee.getEmail()); // Update email if provided
            }
            return employeeRepository.save(existingEmployee); // Save the partially updated employee
        } else {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found"); // Handle employee not found
        }
    }
}

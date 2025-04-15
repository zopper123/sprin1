package com.example.Employe.Management.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
// Removed redundant PathVariable import as it's already covered by the wildcard import

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeService employeService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping
    public List<EmployeeEntity> getAllEmployees() {
        return employeService.readEmployees();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('EDITOR')")
    @PostMapping
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity employee) {
        return employeService.createEmployee(employee);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        return employeService.deleteEmployee(id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PutMapping("/{id}")
    public EmployeeEntity updateEmployee(@PathVariable Long id, @RequestBody EmployeeEntity employee) {
        return employeService.updateEmployee(id, employee);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR')")
    @PatchMapping("/{id}")
    public EmployeeEntity partialUpdateEmployee(@PathVariable Long id, @RequestBody EmployeeEntity employee) {
        return employeService.partialUpdateEmployee(id, employee);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('EDITOR') or hasRole('OPERATOR')")
    @GetMapping("/permissions")
    public Map<String, Boolean> getRolePermissions() {
        Map<String, Boolean> permissions = new HashMap<>();
        permissions.put("GET_ALL_EMPLOYEES", SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN") || a.getAuthority().equals("ROLE_USER")));
        permissions.put("CREATE_EMPLOYEE", SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN") || a.getAuthority().equals("EDITOR")));
        permissions.put("DELETE_EMPLOYEE", SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
        permissions.put("UPDATE_EMPLOYEE", SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN") || a.getAuthority().equals("USER")));
        permissions.put("PARTIAL_UPDATE_EMPLOYEE", SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN") || a.getAuthority().equals("OPERATOR")));
        return permissions;
    }
}

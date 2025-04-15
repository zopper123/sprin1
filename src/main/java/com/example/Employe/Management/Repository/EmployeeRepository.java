package com.example.Employe.Management.Repository;

import com.example.Employe.Management.Controller.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    // Custom queries can be added here if needed
}

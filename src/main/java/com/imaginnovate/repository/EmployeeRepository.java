package com.imaginnovate.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imaginnovate.entity.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    // Additional methods if needed
}

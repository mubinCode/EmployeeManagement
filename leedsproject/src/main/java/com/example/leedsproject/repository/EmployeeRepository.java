package com.example.leedsproject.repository;

import com.example.leedsproject.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByFirstnameIgnoreCase(String firstname);
}

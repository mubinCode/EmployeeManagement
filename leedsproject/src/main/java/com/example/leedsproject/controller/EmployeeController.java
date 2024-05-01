package com.example.leedsproject.controller;

import com.example.leedsproject.dto.EmployeeRequestDto;
import com.example.leedsproject.model.Employee;
import com.example.leedsproject.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/addEmployee", produces = {"application/json"})
    public String add(){
        return employeeService.add();
    }
    @GetMapping("/getAllEmployee")
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }
    @GetMapping("/getEmployee")
    public Employee getEmployee(@RequestParam int employeeId){
        return employeeService.getEmployee(employeeId);
    }
    @PutMapping("/updateEmployee/{firstName}")
    public Employee updateEmployee(@RequestBody EmployeeRequestDto employeeRequestDto, @PathVariable String firstName){
        return employeeService.update(employeeRequestDto, firstName);
    }
    @DeleteMapping("/deleteEmployee")
    public String deleteEmployee(){
        return employeeService.deleteEmployee();
    }
}

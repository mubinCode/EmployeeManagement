package com.example.leedsproject.service;

import com.example.leedsproject.dto.EmployeeDto;
import com.example.leedsproject.dto.EmployeeRequestDto;
import com.example.leedsproject.model.Employee;
import com.example.leedsproject.repository.EmployeeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public String add() {
        InputStream xmlResource = EmployeeService.class.getClassLoader().getResourceAsStream("employee.xml");
        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLStreamReader reader;
        try {
            reader = factory.createXMLStreamReader(xmlResource);
        }catch (XMLStreamException ex){
            throw new RuntimeException("Input steam reader exception.");
        }

        XmlMapper mapper = new XmlMapper();
        List<EmployeeDto> employeeDtos;

        try {
            employeeDtos = mapper.readValue(reader, new TypeReference<>() {});
        } catch (IOException ex) {
            throw new RuntimeException("Can't map xml to Java object.");
        }

        List<Employee> employees = employeeDtos.stream().map(employeeDto ->
                new Employee(Integer.parseInt(employeeDto.getId()),
                employeeDto.getFirstname(), employeeDto.getLastname(),
                        employeeDto.getTitle(), employeeDto.getDivision(), employeeDto.getBuilding(),
                        employeeDto.getRoom())).toList();

        try {
            employeeRepository.saveAll(employees);
        return "Employee saved succussfully.";
        }catch (Exception ex){
            throw new RuntimeException("Employee addition operation has been failed.");
        }

    }

    public List<Employee> getAllEmployee() {
        try {
            return employeeRepository.findAll();
        }catch (Exception ex){
            throw new RuntimeException("Employee fetching operation has been failed.");
        }
    }

    public Employee getEmployee(int employeeId) {
        try {
            return employeeRepository.findById(employeeId).orElseThrow();
        }catch (Exception ex){
            throw new RuntimeException("No user found with provided Id.");
        }
    }

    public Employee update(EmployeeRequestDto employeeRequestDto, String firstName) {
        try {
            Employee updatableEmployee = employeeRepository.findByFirstnameIgnoreCase(firstName).orElseThrow();
            if(Objects.nonNull(updatableEmployee)) {
                updatableEmployee.setFirstname(employeeRequestDto.getFirstname());
                updatableEmployee.setLastname(employeeRequestDto.getLastname());
                updatableEmployee.setDivision(employeeRequestDto.getDivision());
                updatableEmployee.setTitle(employeeRequestDto.getTitle());
                updatableEmployee.setBuilding(employeeRequestDto.getBuilding());
                updatableEmployee.setRoom(employeeRequestDto.getRoom());
                try {
                    employeeRepository.save(updatableEmployee);
                }catch (Exception ex){
                    throw new RuntimeException("\"Employee update operation has been failed.\"");
                }
            }
                return updatableEmployee;
        }catch (Exception ex){
            throw new RuntimeException("No user found with provided firstname.");
        }
    }

    public String deleteEmployee() {
        try {
            employeeRepository.deleteAll();
        return "All employee has been deleted.";
        }catch (Exception ex){
            throw new RuntimeException("Delete oparation has been failed.");
        }
    }
}

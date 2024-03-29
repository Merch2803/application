package com.epam.controllers;

import com.epam.entity.EmployeeDto;
import com.epam.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable String id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping()
    public List<EmployeeDto> getEmployees() {
        return employeeService.getEmployees();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable String id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok("The employee has been deleted successfully!");
    }
}

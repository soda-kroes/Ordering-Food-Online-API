package com.soda.controller;

import com.soda.model.Employee;
import com.soda.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping()
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        Employee res = employeeService.addEmployee(employee);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> getAllEmployees() {
        List<Employee> res = employeeService.getAllEmployees();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}

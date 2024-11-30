package com.soda.services.impl;

import com.soda.model.Employee;
import com.soda.repository.EmployeeRepository;
import com.soda.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employee employee) {
        Employee newEmployee = new Employee();
        newEmployee.setName(employee.getName());
        newEmployee.setPhoneNumbers(employee.getPhoneNumbers());
        return employeeRepository.save(newEmployee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}

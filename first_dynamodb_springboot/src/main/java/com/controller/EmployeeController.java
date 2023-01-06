package com.controller;

import com.entity.Employee;
import com.repository.EmployeeRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/getmessage")
    public String getEmplyee() {
        return "Employee found";
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployee(){
    	return employeeRepository.getAll();
    }
    
    
    
    @PostMapping("/save")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping("/getemployee/{employeeId}")
    public Employee getEmplyee(@PathVariable String employeeId) {
        return employeeRepository.getEmployeeById(employeeId);
    }

    @DeleteMapping("/deleteemployee/{employeeId}")
    public String deleteEmployee(@PathVariable String employeeId) {
        return employeeRepository.deleteEmployee(employeeId);
    }

    @PutMapping("/updateemployee/{employeeId}")
    public String updateEmployee(@PathVariable String employeeId, @RequestBody Employee employee) {
        return employeeRepository.updateEmployee(employeeId, employee);
    }

}

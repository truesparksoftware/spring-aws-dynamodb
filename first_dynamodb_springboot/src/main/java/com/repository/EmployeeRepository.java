package com.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.entity.Employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {

    @Autowired(required=true)
    private DynamoDBMapper dynamoDBMapper;

    public Employee save(Employee employee) {
        dynamoDBMapper.save(employee);
        return employee;
    }

    public List<Employee> getAll(){
    	List<Employee> list=dynamoDBMapper.scan(Employee.class,new DynamoDBScanExpression());
    	return list;
    }
    public Employee getEmployeeById(String employeeId) {
        Employee employee = dynamoDBMapper.load(Employee.class, employeeId);
        return employee;
    }

    public String deleteEmployee(String employeeId) {
        Employee employee = dynamoDBMapper.load(Employee.class, employeeId);
        dynamoDBMapper.delete(employee);
        return "Employee deleted";
    }

    public String updateEmployee(String employeeId, Employee employee) {
        dynamoDBMapper.save(employee,new DynamoDBSaveExpression().withExpectedEntry("employeeId",
                new ExpectedAttributeValue(
                        new AttributeValue().withS(employeeId)
                )));
        return employeeId;
    }
}

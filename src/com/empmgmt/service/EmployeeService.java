package com.empmgmt.service;

import com.empmgmt.exception.EmployeeNotFoundException;
import com.empmgmt.model.Employee;

import java.util.List;

public interface EmployeeService {

    void addEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee searchById(int employeeId) throws EmployeeNotFoundException;

    List<Employee> getByDepartment(String department);

    List<Employee> getActiveEmployeesWithSalaryGreaterThan(double salaryThreshold);
}

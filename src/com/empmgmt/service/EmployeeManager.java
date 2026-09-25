package com.empmgmt.service;

import com.empmgmt.exception.DuplicateEmployeeException;
import com.empmgmt.exception.EmployeeNotFoundException;
import com.empmgmt.model.Employee;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeManager implements EmployeeService {

    private final Map<Integer, Employee> employeeStore = new LinkedHashMap<>();

    @Override
    public void addEmployee(Employee employee) {
        if (employeeStore.containsKey(employee.getEmployeeId())) {
            throw new DuplicateEmployeeException(employee.getEmployeeId());
        }
        employeeStore.put(employee.getEmployeeId(), employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return Collections.unmodifiableList(
                employeeStore.values().stream().collect(Collectors.toList()));
    }

    @Override
    public Employee searchById(int employeeId) throws EmployeeNotFoundException {
        Optional<Employee> found = Optional.ofNullable(employeeStore.get(employeeId));
        return found.orElseThrow(() -> new EmployeeNotFoundException(employeeId));
    }

    @Override
    public List<Employee> getByDepartment(String department) {
        return employeeStore.values().stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> getActiveEmployeesWithSalaryGreaterThan(double salaryThreshold) {
        return employeeStore.values().stream()
                .filter(Employee::isActive)
                .filter(e -> e.getSalary() > salaryThreshold)
                .collect(Collectors.toList());
    }
}

package com.empmgmt.exception;

public class EmployeeNotFoundException extends Exception {

    public EmployeeNotFoundException(String message) {
        super(message);
    }

    public EmployeeNotFoundException(int employeeId) {
        super("Employee with ID " + employeeId + " was not found.");
    }
}

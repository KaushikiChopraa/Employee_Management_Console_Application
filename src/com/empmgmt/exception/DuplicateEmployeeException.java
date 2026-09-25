package com.empmgmt.exception;

public class DuplicateEmployeeException extends RuntimeException {

    public DuplicateEmployeeException(int employeeId) {
        super("Employee with ID " + employeeId + " already exists.");
    }
}

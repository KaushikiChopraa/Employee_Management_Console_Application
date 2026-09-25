package com.empmgmt.model;

public class Employee {

    public enum Status {
        ACTIVE,
        INACTIVE;

        public boolean isActive() {
            return this == ACTIVE;
        }
    }

    private final int employeeId;
    private String name;
    private String department;
    private double salary;
    private Status status;

    public Employee(int employeeId, String name, String department, double salary, Status status) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.status = status;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isActive() {
        return status.isActive();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative.");
        }
        this.salary = salary;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%-5d | %-10s | %-12s | %-10.2f | %s",
                employeeId, name, department, salary, status);
    }
}

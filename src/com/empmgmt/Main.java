package com.empmgmt;

import com.empmgmt.exception.DuplicateEmployeeException;
import com.empmgmt.exception.EmployeeNotFoundException;
import com.empmgmt.model.Employee;
import com.empmgmt.service.EmployeeManager;
import com.empmgmt.service.EmployeeService;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final EmployeeService employeeService = new EmployeeManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedSampleData();
        runMenuLoop();
        scanner.close();
        System.out.println("Application closed. Goodbye!");
    }

    private static void seedSampleData() {
        employeeService.addEmployee(new Employee(101, "Alex", "Engineering", 90000, Employee.Status.ACTIVE));
        employeeService.addEmployee(new Employee(102, "Sam", "Engineering", 125000, Employee.Status.ACTIVE));
        employeeService.addEmployee(new Employee(103, "John", "Finance", 140000, Employee.Status.INACTIVE));
        employeeService.addEmployee(new Employee(104, "Priya", "Engineering", 150000, Employee.Status.ACTIVE));
    }

    private static void runMenuLoop() {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> addEmployeeFlow();
                    case "2" -> displayAllEmployees();
                    case "3" -> searchByIdFlow();
                    case "4" -> displayByDepartmentFlow();
                    case "5" -> displayActiveAboveSalaryFlow();
                    case "6" -> running = false;
                    default -> System.out.println("Invalid choice. Please select a valid menu option.");
                }
            } catch (DuplicateEmployeeException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (EmployeeNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid numeric value.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("===== Employee Management Console =====");
        System.out.println("1. Add Employee");
        System.out.println("2. Display All Employees");
        System.out.println("3. Search Employee by ID");
        System.out.println("4. Display Employees by Department");
        System.out.println("5. Display Active Employees with Salary Greater Than X");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addEmployeeFlow() {
        System.out.print("Enter Employee ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter Department: ");
        String department = scanner.nextLine().trim();

        System.out.print("Enter Salary: ");
        double salary = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Enter Status (ACTIVE/INACTIVE): ");
        String statusInput = scanner.nextLine().trim().toUpperCase();
        Employee.Status status = Employee.Status.valueOf(statusInput);

        Employee employee = new Employee(id, name, department, salary, status);
        employeeService.addEmployee(employee);
        System.out.println("Employee added successfully.");
    }

    private static void displayAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        printHeader();
        employees.forEach(System.out::println);
    }

    private static void searchByIdFlow() throws EmployeeNotFoundException {
        System.out.print("Enter Employee ID to search: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        Employee employee = employeeService.searchById(id);
        printHeader();
        System.out.println(employee);
    }

    private static void displayByDepartmentFlow() {
        System.out.print("Enter Department name: ");
        String department = scanner.nextLine().trim();
        List<Employee> employees = employeeService.getByDepartment(department);
        if (employees.isEmpty()) {
            System.out.println("No employees found in department: " + department);
            return;
        }
        printHeader();
        employees.forEach(System.out::println);
    }

    private static void displayActiveAboveSalaryFlow() {
        System.out.print("Enter minimum salary threshold: ");
        double threshold = Double.parseDouble(scanner.nextLine().trim());
        List<Employee> employees = employeeService.getActiveEmployeesWithSalaryGreaterThan(threshold);
        if (employees.isEmpty()) {
            System.out.println("No active employees found above salary: " + threshold);
            return;
        }
        printHeader();
        employees.forEach(System.out::println);
    }

    private static void printHeader() {
        System.out.println(String.format("%-5s | %-10s | %-12s | %-10s | %s",
                "ID", "Name", "Department", "Salary", "Status"));
        System.out.println("-------------------------------------------------------------");
    }
}

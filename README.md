# Employee Management Console Application

A simple Java console application demonstrating Java fundamentals, OOP, the
Collections Framework, exception handling, and Java 8+ features (Streams,
lambdas, `Optional`, switch expressions).

## Project Structure

```
EmployeeManagementConsole/
├── src/
│   └── com/empmgmt/
│       ├── Main.java                          # Console UI / entry point
│       ├── model/
│       │   └── Employee.java                  # Employee entity (encapsulation, Status enum)
│       ├── exception/
│       │   ├── EmployeeNotFoundException.java # Custom checked exception
│       │   └── DuplicateEmployeeException.java# Custom unchecked exception
│       └── service/
│           ├── EmployeeService.java           # Interface (abstraction)
│           └── EmployeeManager.java           # Implementation (Map-based storage, Streams)
├── sample-output.txt                          # Sample run transcript (text)
├── screenshot-sample-run.png                  # Screenshot of the sample run
└── README.md
```

## Requirements

- Java Development Kit (JDK) 17 or later (uses switch expressions; developed/tested on JDK 21)

## How to Compile and Run

1. Extract the zip file and open a terminal in the `EmployeeManagementConsole` folder.

2. Compile the source files:

   ```bash
   javac -d out $(find src -name "*.java")
   ```

   On Windows (PowerShell), you can instead run:

   ```powershell
   javac -d out (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object { $_.FullName })
   ```

3. Run the application:

   ```bash
   java -cp out com.empmgmt.Main
   ```

4. You will see a menu-driven console:

   ```
   ===== Employee Management Console =====
   1. Add Employee
   2. Display All Employees
   3. Search Employee by ID
   4. Display Employees by Department
   5. Display Active Employees with Salary Greater Than X
   6. Exit
   Enter your choice:
   ```

   The application pre-loads four sample employees (matching the assignment
   example: Alex, Sam, John, Priya) so you can try search/filter options
   immediately, and you can also add your own employees via option 1.

## Feature-to-Concept Mapping

| Feature | Where it's demonstrated |
|---|---|
| Variables, data types, methods, loops, conditionals | `Main.java` menu loop, input parsing |
| Classes & objects | `Employee` |
| Encapsulation | Private fields with getters/setters in `Employee`, validation in `setSalary` |
| Interfaces / abstraction | `EmployeeService` interface implemented by `EmployeeManager` |
| Collections Framework | `LinkedHashMap<Integer, Employee>` in `EmployeeManager` for O(1) ID lookup while preserving insertion order |
| Exception handling | `EmployeeNotFoundException` (checked), `DuplicateEmployeeException` (unchecked), `try/catch` blocks in `Main` for invalid numeric input |
| Lambda / Stream API | `EmployeeManager.getByDepartment`, `getActiveEmployeesWithSalaryGreaterThan` (filter by department; filter active + salary, mirroring the assignment's example) |
| Bonus: Java 8+ features | `Optional` (`searchById`), enum `Status`, switch expressions (`Main.runMenuLoop`) |

## Sample Output / Screenshot

See `screenshot-sample-run.png` for a screenshot of a successful run, and
`sample-output.txt` for the same run as plain text. The sample run:
1. Displays all seeded employees
2. Searches for an existing employee (ID 102 → Sam)
3. Searches for a non-existent employee (ID 999) to show the
   `EmployeeNotFoundException` being handled gracefully
4. Filters employees by department ("Engineering")
5. Filters active employees with salary > 100,000 (matches the assignment
   example: returns Sam and Priya)
6. Adds a new employee and displays the updated list

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

class Employee {
    private static int nextId = 1;
    private final int id;
    private final String name;
    private double salary;
    private double taxRate;
    private double niRate;
    
public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getTaxRate() {
        return taxRate;
    }
    // Save the database to a file
try {
payrollSystem.saveDatabaseToFile("employees.txt");
} catch (IOException e) {
System.err.println("Error saving database to file: " + e.getMessage());
}

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getNiRate() {
        return niRate;
    }

    public void setNiRate(double niRate) {
        this.niRate = niRate;
    }

    public double getGrossPay() {
        return salary;
    }

    public double getTax() {
        return salary * taxRate;
    }

    public double getNationalInsurance() {
        return salary * niRate;
    }

    public double getNetPay() {
        return salary - getTax() - getNationalInsurance();
    }
    
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Salary: " + salary + ", Tax Rate: " + taxRate + ", NI Rate: "
                + niRate;
    }
}

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        
    }
}
class PayrollSystem {
private final EmployeeDatabase employeeDatabase = new EmployeeDatabase();
public void addEmployee(String name, double salary, double taxRate, double niRate) {
Employee employee = new Employee(name, salary, taxRate, niRate);
employeeDatabase.addEmployee(employee);
}    
public void payEmployees() {
List<Employee> employees = employeeDatabase.getAllEmployees();
for (Employee employee : employees) {
double netPay = employee.getNetPay();
System.out.println("Pay Slip for " + employee.getName());
System.out.println("Gross Pay: " + employee.getGrossPay());
System.out.println("Tax: " + employee.getTax());
System.out.println("National Insurance: " + employee.getNationalInsurance());
System.out.println("Net Pay: " + netPay);
System.out.println("------------------------");
employee.setSalary(employee.getSalary() + netPay); // Add net pay to next month's salary
}
}    
public void saveDatabaseToFile(String fileName) throws IOException {
employeeDatabase.saveToFile(fileName);
}

public void loadDatabaseFromFile(String fileName) throws IOException {
employeeDatabase.loadFromFile(fileName);
}
}
public class Main {
public static void main(String[] args) {
PayrollSystem payrollSystem = new PayrollSystem();
// Add employees to the database
payrollSystem.addEmployee("John Smith", 3000.0, 0.2, 0.1);
payrollSystem.addEmployee("Jane Doe", 4000.0, 0.25, 0.15);
// Save the database to a file
try {
payrollSystem.saveDatabaseToFile("employees.txt");
} catch (IOException e) {
System.err.println("Error saving database to file: " + e.getMessage());
}
// Load the database from a file
try {
payrollSystem.loadDatabaseFromFile("employees.txt");
} catch (IOException e) {
System.err.println("Error loading database from file: " + e.getMessage());
}

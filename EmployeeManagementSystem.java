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
    
    public Employee (String name, double salary, double taxRate, double niRate) {
        this.id = nextId++;
        this.name = name;
        this.salary = salary;
        this.taxRate = taxRate;
        this.niRate = niRate;
    }
    
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

class EmployeeDatabase {
    private final List<Employee> employees = new ArrayList<>();
    
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
    
    public Employee getEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;    
            }
        }
        return null;
    }
    
    public List<Employee> getAllEmployees() {
        return employees;
    }
    
    public void saveToFile(String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Employee employee : employees) {
                writer.println(employee.getId() + "," + employee.getname() + "," + employee.getSalary()
                               + "," + employee.getTaxRate() + "," + employee.getNiRate());
            }
        }
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
            employee.setSalary(employee.getSalary() + netPay);
        }
    }
    
    public void saveDatabaseToFile(String fileName) throws IOException {
        employeeDatabase.saveToFile(fileName);
    }

    public void loadDatabaseFromFile(String fileName) throws IOException {
        employeeDatabase.loadFromFile(fileName);
    }
}

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        PayrollSystem payrollSystem = new PayrollSystem();
        
        payrollSystem.addEmployee("John Smith", 3000.0, 0.2, 0.1);
        payrollSystem.addEmployee("Jane Doe", 4000.0, 0.25, 0.15);
        
        try {
            payrollSystem.saveDatabaseToFile("employees.txt");
        } catch (IOException e) {
            System.err.println("Error saving database to file: " + e.getMessage());
        }
        
        try {
            payrollSystem.loadDatabaseFromFile("employees.txt");
        } catch (IOException e) {
            System.err.println("Error loading database from file: " + e.getMessage());
        }
        
        payrollSystem.payEmployees();
    }
}


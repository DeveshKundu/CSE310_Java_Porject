import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

class Employee {
    static int nextId = 1;
    final int id;
    final String name;
    double salary;
    double taxRate;
    double niRate;
    
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
    final ArrayList<Employee> employees = new ArrayList<>();
    
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
    
    public ArrayList<Employee> getAllEmployees() {
        return employees;
    }
    
    public void saveToFile(String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Employee employee : employees) {
                writer.println(employee.getId() + "," + employee.getName() + "," + employee.getSalary()
                               + "," + employee.getTaxRate() + "," + employee.getNiRate());
            }
        }
    }
    
    public void loadFromFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                Integer.parseInt(fields[0]);
                String name = fields[1];
                double salary = Double.parseDouble(fields[2]);
                double taxRate = Double.parseDouble(fields[3]);
                double niRate = Double.parseDouble(fields[4]);
                employees.add(new Employee(name, salary, taxRate, niRate));
                Employee nextEmployee = new Employee(name, salary, taxRate, niRate);
                nextEmployee.getId();
            }
        }
    }
}

class PayrollSystem {
    final EmployeeDatabase employeeDatabase = new EmployeeDatabase();
    public void addEmployee(String name, double salary, double taxRate, double niRate) {
        Employee employee = new Employee(name, salary, taxRate, niRate);
        employeeDatabase.addEmployee(employee);
    }
    
    public void payEmployees() {
        ArrayList<Employee> employees = employeeDatabase.getAllEmployees();
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

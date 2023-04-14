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

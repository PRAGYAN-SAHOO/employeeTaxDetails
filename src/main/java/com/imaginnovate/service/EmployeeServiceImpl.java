package com.imaginnovate.service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imaginnovate.entity.Employee;
import com.imaginnovate.entity.EmployeeTaxDetails;
import com.imaginnovate.repository.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void addEmployee(Employee employee) {
        // Save employee to repository
        employeeRepository.save(employee);
    }

   
    public List<EmployeeTaxDetails> calculateTaxDeduction() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeTaxDetails> taxDetailsList = new ArrayList<>();

        for (Employee employee : employees) {
            double yearlySalary = calculateYearlySalary(employee);
            double taxAmount = calculateTaxAmount(yearlySalary);
            double cessAmount = calculateCessAmount(yearlySalary);
            
            EmployeeTaxDetails taxDetails = new EmployeeTaxDetails();
            taxDetails.setEmployeeId(employee.getEmployeeId());
            taxDetails.setFirstName(employee.getFirstName());
            taxDetails.setLastName(employee.getLastName());
            taxDetails.setYearlySalary(yearlySalary);
            taxDetails.setTaxAmount(taxAmount);
            taxDetails.setCessAmount(cessAmount);
            
            taxDetailsList.add(taxDetails);
        }

        return taxDetailsList;
    }

    private double calculateYearlySalary(Employee employee) {
        LocalDate now = LocalDate.now();
        int monthsWorked = employee.getDoj().until(now).getMonths() + 1; // Adding 1 to include the current month
        double yearlySalary = employee.getSalary() * monthsWorked / 12;
        return yearlySalary;
    }

    private double calculateCessAmount(double yearlySalary) {
        double cessAmount = 0;

        // Collect additional 2% cess for the amount more than 2500000
        if (yearlySalary > 2500000) {
            cessAmount = (yearlySalary - 2500000) * 0.02;
        }

        return cessAmount;
    }


   
    private double calculateTaxAmount(double yearlySalary) {
        double taxAmount = 0;

        if (yearlySalary <= 250000) {
            // No tax for salary <= 250000
            taxAmount = 0;
        } else if (yearlySalary > 250000 && yearlySalary <= 500000) {
            // 5% tax for salary > 250000 and <= 500000
            taxAmount = (yearlySalary - 250000) * 0.05;
        } else if (yearlySalary > 500000 && yearlySalary <= 1000000) {
            // 10% tax for salary > 500000 and <= 1000000
            taxAmount = (250000 * 0.05) + ((yearlySalary - 500000) * 0.1);
        } else {
            // 20% tax for salary > 1000000
            taxAmount = (250000 * 0.05) + (500000 * 0.1) + ((yearlySalary - 1000000) * 0.2);
        }

        return taxAmount;
    }

}

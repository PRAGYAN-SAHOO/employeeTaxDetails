package com.imaginnovate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.imaginnovate.entity.Employee;
import com.imaginnovate.entity.EmployeeTaxDetails;
public interface EmployeeService {
	 public void addEmployee(Employee employee);
	 public List<EmployeeTaxDetails> calculateTaxDeduction();

}

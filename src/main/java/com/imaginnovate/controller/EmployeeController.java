package com.imaginnovate.controller;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.imaginnovate.entity.Employee;
import com.imaginnovate.entity.EmployeeDTO;
import com.imaginnovate.entity.EmployeeTaxDetails;
import com.imaginnovate.service.EmployeeService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@Validated @RequestBody EmployeeDTO employee, BindingResult result) {
    	System.out.println("employee"+employee);
    	//employee.getPhoneNumbers().forEach(System.out::println);
        if(result.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }
        Employee employee2=new Employee();
        BeanUtils.copyProperties(employee, employee2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        employee2.setDoj(LocalDate.parse(employee.getDoj(),formatter));
        employeeService.addEmployee(employee2);
        return ResponseEntity.ok("Employee added successfully");
    }

    @GetMapping("/tax-deduction")
    public ResponseEntity<?> getTaxDeduction() {
        List<EmployeeTaxDetails> taxDetails = employeeService.calculateTaxDeduction();
        return ResponseEntity.ok(taxDetails);
    }
}

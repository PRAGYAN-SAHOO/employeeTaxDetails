package com.imaginnovate.entity;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Employee {
		@Id
	    @NotBlank
	    private String employeeId;
	    @NotBlank
	    private String firstName;
	    @NotBlank
	    private String lastName;
	    @NotBlank
	    @Email
	    private String email;
	    //@ElementCollection
	    private String phoneNumbers;
	    private LocalDate doj;
	    @NotNull
	    @Positive
	    private double salary;
	    
		public Employee() {
			super();
		}
		public Employee(@NotBlank String employeeId, @NotBlank String firstName, @NotBlank String lastName,
				@NotBlank @Email String email, String phoneNumbers, LocalDate doj,
				@NotNull @Positive double salary) {
			super();
			this.employeeId = employeeId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.phoneNumbers = phoneNumbers;
			this.doj = doj;
			this.salary = salary;
		}
		public String getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(String employeeId) {
			this.employeeId = employeeId;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhoneNumbers() {
			return phoneNumbers;
		}
		public void setPhoneNumbers(String phoneNumbers) {
			this.phoneNumbers = phoneNumbers;
		}
		public LocalDate getDoj() {
			return doj;
		}
		public void setDoj(LocalDate doj) {
			this.doj = doj;
		}
		public double getSalary() {
			return salary;
		}
		public void setSalary(double salary) {
			this.salary = salary;
		}
		@Override
		public String toString() {
			return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
					+ ", email=" + email + ", phoneNumbers=" + phoneNumbers + ", doj=" + doj + ", salary=" + salary
					+ "]";
		}
	

}

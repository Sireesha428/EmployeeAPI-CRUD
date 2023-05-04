package com.last.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.last.model.Employee;
import com.last.repository.EmployeeRepository;

@RestController
@RefreshScope
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	@Value("${changecity}")
    private String changecity;
	
	// POST method to create a new employee
	@PostMapping("/employees")
	public String createNewEmployee(@RequestBody Employee employee) {
		employee.setEmp_city(changecity);
		employeeRepository.save(employee); // save the new employee to the database
		return "Employee Created in database";		
	}
	
	// GET method to retrieve all employees
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> empList = new ArrayList<>();
		employeeRepository.findAll().forEach(empList::add); // retrieve all employees from the database and add them to a list
		return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK); // return the list of employees with a HTTP status code of 200 (OK)
	}
	
	// GET method to retrieve an employee by ID
	@GetMapping("/employees/{empid}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long empid) {
		Optional<Employee> emp = employeeRepository.findById(empid); // retrieve the employee with the given ID from the database (if it exists)
		if (emp.isPresent()) {
			return new ResponseEntity<Employee>(emp.get(), HttpStatus.FOUND); // return the employee with a HTTP status code of 302 (FOUND)
		} else {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND); // return a HTTP status code of 404 (NOT FOUND) if the employee is not found
		}
	}
	
	// PUT method to update an employee by ID
	@PutMapping("/employees/{empid}")
	public String updateEmployeeById(@PathVariable long empid, @RequestBody Employee employee) {
		Optional<Employee> emp = employeeRepository.findById(empid); // retrieve the employee with the given ID from the database (if it exists)
		if (emp.isPresent()) {
			Employee existEmp = emp.get();
			existEmp.setEmp_age(employee.getEmp_age()); // update the existing employee's details with the new details provided in the request body
			existEmp.setEmp_city(employee.getEmp_city());
			existEmp.setEmp_name(employee.getEmp_name());
			existEmp.setEmp_salary(employee.getEmp_salary());
			employeeRepository.save(existEmp); // save the updated employee to the database
			return "Employee Details against Id " + empid + " updated"; // return a success message
		} else {
			return "Employee Details does not exist for empid " + empid; // return an error message if the employee is not found
		}
	}
	
	// DELETE method to delete an employee by ID
	@DeleteMapping("/employees/{empid}")
	public String deleteEmployeeByEmpId(@PathVariable Long empid) {
		employeeRepository.deleteById(empid); // delete the employee with the given ID from the database
		return "Employee Deleted Successfully"; // return a success message
	}

	// DELETE method to delete all employees
	@DeleteMapping("/employees")
	public String deleteAllEmployee() {
		employeeRepository.deleteAll(); // delete all employees from the database
		return "Employee deleted Successfully..";
	}
	
}

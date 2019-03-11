package com.karle.CRUDspringMySQL.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karle.CRUDspringMySQL.exception.EmployeeNotFound;
import com.karle.CRUDspringMySQL.model.Employee;
import com.karle.CRUDspringMySQL.repository.EmployeeRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeRepository employeeRepository;

	EmployeeController(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	/* CRUD OPERATIONS */

	/* Get all Employees */
	@GetMapping
	public List<?> findAll() {
		return employeeRepository.findAll();
	}

	// Get Employee by ID
	@GetMapping("/{id}")
	public Employee retrieveStudent(@PathVariable long id) {
		Optional<Employee> employee = employeeRepository.findById(id);

		if (!employee.isPresent())
			throw new EmployeeNotFound();
		// System.out.println("Employee Not Found");
		return employee.get();
	}

	// Insert Employee
	@PostMapping
	public Employee create(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	// Update an Employee
	@PutMapping(value = "/{empId}")
	public ResponseEntity<Employee> update(@PathVariable("empId") long empId, @RequestBody Employee employee) {
		return employeeRepository.findById(empId).map(record -> {
			record.setFirstName(employee.getFirstName());
			record.setLastName(employee.getLastName());
			record.setEmail(employee.getEmail());
			record.setDesignation(employee.getDesignation());
			record.setProjectName(employee.getProjectName());
			Employee updated = employeeRepository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElseThrow(() -> new EmployeeNotFound());
	}

	/* Delete Employee By Id */
	@DeleteMapping(path = { "/{empId}" })
	/*
	 * public ResponseEntity<?> delete(@PathVariable("empId") long empId) {
	 * 
	 * 
	 * 
	 * return employeeRepository.findById(empId).map(record -> {
	 * employeeRepository.deleteById(empId); return ResponseEntity.ok().build();
	 * }).orElse(ResponseEntity.notFound().build());
	 * 
	 * }
	 */

	public Employee delete(@PathVariable long empId) {
		Optional<Employee> employee = employeeRepository.findById(empId);

		if (!employee.isPresent())
			throw new EmployeeNotFound();
		// System.out.println("Employee Not Found");

		employeeRepository.deleteById(empId);
		return employee.get();
	}

}

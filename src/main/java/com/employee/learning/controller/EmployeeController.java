package com.employee.learning.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.employee.learning.model.Employee;
import com.employee.learning.service.EmployeeService;

@RestController
@EnableWebMvc
@RequestMapping("/api/v1")
public class EmployeeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@GetMapping(value = "/employee/{id}", headers = "Accept=application/json")
	public ResponseEntity<?> getEmployee(@PathVariable("id") int employeeId) {
		LOGGER.info("In Get Employee");
		return employeeService.getEmployee(employeeId);
	}

	@PostMapping(value = "/employee", headers = "Accept=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
		LOGGER.info("In Post Employee");
		return employeeService.addEmployee(employee);
	}
	
	
	@PutMapping(value = "/employee/{id}", headers = "Accept=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateEmployee(@PathVariable("id") int employeeId, @RequestBody Employee employee) {
		LOGGER.info("In Post Employee");
		return employeeService.updateEmployee(employeeId, employee);
	}
	
	@DeleteMapping(value = "/employee/{id}", headers = "Accept=application/json")
	public ResponseEntity<?> addEmployee(@PathVariable("id")  int employeeId) {
		LOGGER.info("In Post Employee");
		return employeeService.deleteEmployee(employeeId);
	}
	

}

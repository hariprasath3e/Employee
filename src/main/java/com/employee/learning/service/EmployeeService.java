package com.employee.learning.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.employee.learning.controller.EmployeeController;
import com.employee.learning.dao.EmployeeDao;
import com.employee.learning.model.Employee;

@Service
public class EmployeeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeDao employeeDao;

	public ResponseEntity<String> addEmployee(Employee employee) {
		LOGGER.info("Adding employee to Database.. Calling DAO..");
		int addedEmployeeId;
		try {
			addedEmployeeId = employeeDao.addEmployee(employee);
		} catch (Exception e) {
			LOGGER.error("Exception in add employee .." + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception in adding employee ");
		}
		return ResponseEntity.ok().body("Address added success..");

	}

	public ResponseEntity<String> updateEmployee(int id, Employee updatedEmployee) {
		Employee employee = employeeDao.getEmployee(id);
		if (employee == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee Not found..");
		}
		updatedEmployee.setId(employee.getId());
		try {
			int returnedEmployeeId = employeeDao.updateEmployee(updatedEmployee);
		} catch (Exception e) {
			LOGGER.error("Exception in update employee .." + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception in adding employee ");
		}

		return ResponseEntity.ok().body("Address added success..");
	}

	public ResponseEntity<?> getEmployee(int id) {
		LOGGER.info("Get employee to Database.. Calling DAO..");
		Employee employee = employeeDao.getEmployee(id);
		if (employee == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee Not found..");
		}
		return ResponseEntity.ok().body(employee);
	}

	public ResponseEntity<?> deleteEmployee(int employeeId) {
		LOGGER.info("Deleting employee..");
		Employee employee = employeeDao.getEmployee(employeeId);
		if (employee == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee Not found..");
		}
		try {
			Employee deletedEmployee = employeeDao.deleteEmployee(employee);
		} catch (Exception e) {
			LOGGER.error("Exception in add employee .." + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception in adding employee ");
		}
		return ResponseEntity.ok().body("Address added success..");
	}

}

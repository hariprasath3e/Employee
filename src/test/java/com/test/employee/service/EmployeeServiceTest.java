package com.test.employee.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.employee.learning.dao.EmployeeDao;
import com.employee.learning.model.Employee;
import com.employee.learning.service.EmployeeService;
import com.test.employee.Dao.EmployeeDaoTest;
import com.test.employee.config.ServiceConfig;
import com.test.employee.learning.UnitTest;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ServiceConfig.class })
@Category(UnitTest.class)
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeDao employeeDao;

	@Before
	public void beforeclass() {
		assertNotNull(employeeService);
		assertNotNull(employeeDao);
		Mockito.reset(employeeDao);
	}

	@Test
	public void testAddEmployeeSuccess() throws Exception {
		Employee employee = null;
		try {
			employee = EmployeeDaoTest.getEmployee();
		} catch (ParseException e) {
			assertTrue(false);
		}
		when(employeeDao.addEmployee(employee)).thenReturn(100);
		ResponseEntity<String> entity = employeeService.addEmployee(employee);
		assertEquals(entity.getStatusCodeValue(), 200);
	}

	@Test
	public void testAddEmployeeError() throws Exception {
		Employee employee = null;
		try {
			employee = EmployeeDaoTest.getEmployee();
		} catch (ParseException e) {
			assertTrue(false);
		}
		when(employeeDao.addEmployee(employee)).thenThrow(new RuntimeException());
		ResponseEntity<String> entity = employeeService.addEmployee(employee);
		assertEquals(entity.getStatusCodeValue(), 500);
	}

	@Test
	public void testUpdateEmployeeSuccess() {
		Employee employee = null;
		try {
			employee = EmployeeDaoTest.getEmployee();
		} catch (ParseException e) {
			assertTrue(false);
		}
		when(employeeDao.getEmployee(employee.getId())).thenReturn(employee);
		when(employeeDao.updateEmployee(employee)).thenReturn(1);
		ResponseEntity<String> entity = employeeService.updateEmployee(employee.getId(), employee);
		verify(employeeDao,times(1)).updateEmployee(employee);
		assertEquals(entity.getStatusCodeValue(), 200);
	}
	
	@Test
	public void testUpdateEmployeeUpdateError() {
		Employee employee = null;
		try {
			employee = EmployeeDaoTest.getEmployee();
		} catch (ParseException e) {
			assertTrue(false);
		}
		when(employeeDao.getEmployee(employee.getId())).thenReturn(employee);
		when(employeeDao.updateEmployee(employee)).thenThrow(new RuntimeException());
		ResponseEntity<String> entity = employeeService.updateEmployee(employee.getId(), employee);
		verify(employeeDao,times(1)).updateEmployee(any(Employee.class));
		assertEquals(entity.getStatusCodeValue(), 500);
	}
	
	@Test
	public void testUpdateEmployeeNoEmployeeError() {
		Employee employee = null;
		try {
			employee = EmployeeDaoTest.getEmployee();
		} catch (ParseException e) {
			assertTrue(false);
		}
		when(employeeDao.getEmployee(employee.getId())).thenReturn(null);
		ResponseEntity<String> entity = employeeService.updateEmployee(employee.getId(), employee);
		verify(employeeDao,never()).updateEmployee(any(Employee.class));
		assertEquals(entity.getStatusCodeValue(), 404);
	}
	
	@Test
	public void testGetEmployeeSuccess() {
		Employee employee = null;
		try {
			employee = EmployeeDaoTest.getEmployee();
		} catch (ParseException e) {
			assertTrue(false);
		}
		when(employeeDao.getEmployee(employee.getId())).thenReturn(employee);
		ResponseEntity<Employee> entity = (ResponseEntity) employeeService.getEmployee(employee.getId());
		assertEquals(entity.getStatusCodeValue(), 200);
	}

}

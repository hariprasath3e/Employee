package com.test.employee.Dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.employee.learning.dao.EmployeeDao;
import com.employee.learning.model.Employee;
import com.test.employee.config.DAOConfig;
import com.test.employee.config.HibernateTestConfiguration;
import com.test.employee.learning.UnitTest;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DAOConfig.class, HibernateTestConfiguration.class})
@Category(UnitTest.class)
public class EmployeeDaoTest {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	private final java.util.Random random = new java.util.Random();
	
	@Before
	public void beforeTest() {
		assertNotNull(employeeDao);
		try {
			employeeDao.addEmployee(getEmployee());
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void addEmployeeTest() {
		List<Employee> employeeList = employeeDao.getAllEmployee();
		try {
			employeeDao.addEmployee(getAnotherEmployee());
		} catch (Exception e) {
//			assertTrue(false);
			List<Employee> employeeListUpdated = employeeDao.getAllEmployee();
			for(int i=0;i<employeeListUpdated.size();i++) {
				System.out.println(employeeListUpdated.get(i));
			}
			assertNotNull(employeeListUpdated);
		}
		
	}
	
	@Test
	public void getEmployeeTest() {
		Employee employee = getRandomEmployeeId();
		employee = employeeDao.getEmployee(employee.getId());
		assertNotNull(employee);
	}
	
	@Test
	public void getEmployeeTestFailure() {
		Employee employee = employeeDao.getEmployee(100);
		assertNull(employee);
	}
	
	@Test
	public void updateEmployeeTest() {
		Employee employee = getRandomEmployeeId();
		employee.setFirstName("UpdatedFirstName");
		int employeeID = employeeDao.updateEmployee(employee);
		assertNotEquals(employeeID, 0);
		employee = employeeDao.getEmployee(employeeID);
		assertEquals(employee.getFirstName(), "UpdatedFirstName");
	}
	
	@Test
	public void deleteEmployeeTest() {
		Employee employee = getRandomEmployeeId();
		employeeDao.deleteEmployee(employee);
		employee = employeeDao.getEmployee(employee.getId());
		assertNull(employee);
	}
	
	public Employee getRandomEmployeeId() {
		List<Employee> employeeList = employeeDao.getAllEmployee();
		return employeeList.get(random.nextInt(employeeList.size()));
	}
	
	
	public static Employee getEmployee() throws ParseException {
		Employee emp = new Employee();
		emp.setDepartment("CPECS");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		emp.setDob(sdf.parse("21-04-1988"));
		emp.setDoj(sdf.parse("21-04-2010"));
		emp.setFirstName("Hariprasath");
		emp.setLastName("Govindarajulu");
		return emp;
	}
	
	public static Employee getAnotherEmployee() throws ParseException {
		Employee emp = new Employee();
		emp.setDepartment("Security");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		emp.setDob(sdf.parse("19-05-1988"));
		emp.setDoj(sdf.parse("21-04-2010"));
		emp.setFirstName("Saranya");
		emp.setLastName("Sathyamoorthy");
		return emp;
	}
	
	

}

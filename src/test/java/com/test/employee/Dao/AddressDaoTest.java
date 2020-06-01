package com.test.employee.Dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.employee.learning.dao.AddressDao;
import com.employee.learning.dao.EmployeeDao;
import com.employee.learning.model.Address;
import com.employee.learning.model.Employee;
import com.test.employee.config.DAOConfig;
import com.test.employee.config.HibernateTestConfiguration;
import com.test.employee.learning.UnitTest;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DAOConfig.class, HibernateTestConfiguration.class })
@Category(UnitTest.class)
public class AddressDaoTest {

	private final java.util.Random random = new java.util.Random();

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private EmployeeDao employeeDao;

	@Before
	public void beforeClass() throws Exception {
		assertNotNull(addressDao);
		assertNotNull(employeeDao);
		try {
			employeeDao.addEmployee(EmployeeDaoTest.getEmployee());
			employeeDao.addEmployee(EmployeeDaoTest.getAnotherEmployee());
		} catch (ParseException e) {
			assertTrue(false);
		}
	}

	@Test
	public void testAddAddress() {
		Employee employee = getEmployee();
		addressDao.addAddress(employee.getId(), getAddress());
		List<Address> addressList = addressDao.getAllAddress();
		assertNotNull(addressList.get(0));
		assertNotNull(addressList.get(0).getEmployee());
		assertEquals(addressList.get(0).getEmployee().getId(), employee.getId());
	}

	@Test
	public void testgetAddress() {
		Employee employee = getEmployee();
		addressDao.addAddress(employee.getId(), getAddress());
		List<Address> addressList = addressDao.getAllAddress();
		assertNotNull(addressList.get(0));
		Address searchAddress = addressList.get(0);
		Address returnedAddress = addressDao.getAddress(searchAddress.getId(), searchAddress.getEmployee().getId());
		assertNotNull(returnedAddress);
		assertEquals(searchAddress.getId(), returnedAddress.getId());
	}
	
	@Test
	public void testDeleteAddress() {
		Employee employee = getEmployee();
		addressDao.addAddress(employee.getId(), getAddress());
		List<Address> addressList = addressDao.getAllAddress();
		assertNotNull(addressList.get(0));
		Address searchAddress = addressList.get(0);
		Address returnedAddress = addressDao.deleteAddress(searchAddress.getId(), searchAddress.getEmployee().getId());
		assertNotNull(returnedAddress);
		Address deletedAddress = addressDao.getAddress(searchAddress.getId(), searchAddress.getEmployee().getId());
		assertNull(deletedAddress);
	}

	private Employee getEmployee() {
		List<Employee> employeeList = employeeDao.getAllEmployee();
		assertNotNull(employeeList);
		Employee employee = employeeList.get(random.nextInt(employeeList.size()));
		return employee;
	}

	private Address getAddress() {
		Address addressOne = new Address();
		addressOne.setAddressLine("2412 BARKER CIR");
		addressOne.setCity("West Chester");
		addressOne.setState("PA");
		addressOne.setZipcode("19380");
		return addressOne;
	}

	private Address getAnotherAddress() {
		Address addressOne = new Address();
		addressOne.setAddressLine("1170 QUEEN LANE");
		addressOne.setCity("West Chester");
		addressOne.setState("PA");
		addressOne.setZipcode("19382");
		return addressOne;
	}

}

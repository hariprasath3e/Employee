package com.employee.learning.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.employee.learning.model.Address;
import com.employee.learning.model.Employee;

@Repository
public class AddressDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddressDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	public ResponseEntity<?> getAllAddress(int employeeId) {
		LOGGER.info("Adding employee to DB..");
		Session ses = sessionFactory.openSession();
		try {
			ses.beginTransaction();
			return ResponseEntity.ok().body("Yet to implement..");
		} catch (Exception e) {
			LOGGER.error("Exception in get all address " + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Exception in adding employee " + e.getMessage());
		} finally {
			ses.close();
		}
	}
	
	public List<Address> getAllAddress() {
		LOGGER.info("Get Address from DB..");
		Session ses = sessionFactory.openSession();
		try {
			List<Address> addressList = ses.createCriteria(Address.class).list();
			return addressList;
		} catch (Exception e) {
			LOGGER.error("Exception in getting Employee " + e);
			return null;
		} finally {
			ses.close();
		}
	}

	public Address getAddress(int addressId, int employeeId) {
		Session ses = sessionFactory.openSession();
		try {
			ses.beginTransaction();
			Address address = (Address) ses.get(Address.class, addressId);
			if(address == null) {
				return null;
			}
			if(address.getEmployee().getId() == employeeId ) {
				return address;
			}
			return new Address();
		} catch (Exception e) {
			LOGGER.error("Exception in get address " + e);
			return null;
		} finally {
			ses.close();
		}
	}

	public Integer addAddress(int employeeId, Address address) {
		Session ses = sessionFactory.openSession();
		try {
			ses.beginTransaction();
			Employee employee = (Employee) ses.get(Employee.class, employeeId);
			if (employee == null) {
				return 0;
			}
			address.setEmployee(employee);
			Calendar calendar = Calendar.getInstance();
			Date updatedAt = calendar.getTime();
			address.setUpdatedAt(updatedAt);
			int addressId = (int) ses.save(address);
			ses.getTransaction().commit();
			return addressId;
		} catch (Exception e) {
			LOGGER.error("Exception in add address " + e);
			return null;
		} finally {
			ses.close();
		}
	}

	public Address deleteAddress(int addressId, int employeeId) {
		Session ses = sessionFactory.openSession();
		try {
			ses.beginTransaction();
			Address address = (Address) ses.get(Address.class, addressId);
			LOGGER.info("Address from DB " + address);
			LOGGER.info("Employee Id from input " + employeeId);
			if (address.getEmployee().getId() == employeeId) {
				address.setEmployee(null);
				ses.delete(address);
				ses.getTransaction().commit();
				return address;
			}
			return new Address();
		} catch (Exception e) {
			LOGGER.error("Exception in delete address " + e);
			return null;
		} finally {
			ses.close();
		}
	}

}

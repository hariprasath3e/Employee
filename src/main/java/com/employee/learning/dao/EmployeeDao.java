package com.employee.learning.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.employee.learning.controller.EmployeeController;
import com.employee.learning.model.Employee;

@Repository
public class EmployeeDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public int addEmployee(Employee employee)  throws Exception {
		LOGGER.info("Adding employee to DB..");
		Session ses = sessionFactory.openSession();
		try {
			int addedEmployeeId = (Integer) ses.save(employee);
			return addedEmployeeId;
		} finally {
			ses.close();
		}

	}

	public Employee getEmployee(int id) {
		LOGGER.info("Get employee from DB..");
		Session ses = sessionFactory.openSession();
		try {
			Employee employee = (Employee) ses.get(Employee.class, id);
			return employee;
		} catch (Exception e) {
			LOGGER.error("Exception in getting Employee " + e);
			return null;
		} finally {
			ses.close();
		}
	}

	public List<Employee> getAllEmployee() {
		LOGGER.info("Get employee from DB..");
		Session ses = sessionFactory.openSession();
		try {
			List<Employee> employeeList = ses.createCriteria(Employee.class).list();
			return employeeList;
		} catch (Exception e) {
			LOGGER.error("Exception in getting Employee " + e);
			return null;
		} finally {
			ses.close();
		}
	}

	@Transactional
	public Employee deleteEmployee(Employee employee) {
		LOGGER.info("Deleting employee..");
		Session ses = sessionFactory.openSession();
		try {
			ses.beginTransaction();
			ses.delete(employee);
			ses.getTransaction().commit();
			return employee;
		}  finally {
			ses.close();
		}
	}

	@Transactional
	public int updateEmployee(Employee updatedEmployee) {
		LOGGER.info("updating employee..");
		Session ses = sessionFactory.openSession();
		try {
			ses.beginTransaction();
			int updatedEmployeeId = (Integer) ses.save(updatedEmployee);
			ses.getTransaction().commit();
			return updatedEmployeeId;
		}  finally {
			ses.close();
		}
	}

}

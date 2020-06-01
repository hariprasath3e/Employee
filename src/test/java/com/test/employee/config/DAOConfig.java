package com.test.employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.employee.learning.dao.AddressDao;
import com.employee.learning.dao.EmployeeDao;

@Configuration
public class DAOConfig {
	
	@Bean
	public EmployeeDao employeeDao() {
		return new EmployeeDao();
	}
	
	@Bean
	public AddressDao addressDao() {
		return new AddressDao();
	}

}

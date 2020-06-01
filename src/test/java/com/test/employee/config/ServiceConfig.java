package com.test.employee.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import com.employee.learning.dao.EmployeeDao;
import com.employee.learning.service.EmployeeService;

@Configuration
public class ServiceConfig {
	
	@Bean
	public EmployeeDao employeeDao() {
		return Mockito.mock(EmployeeDao.class);
	}
	
	@Bean
	public EmployeeService employeeService() {
		return new EmployeeService();
	}
	
	@Bean
	public LocalSessionFactoryBean localSessionFactoryBean() {
		return Mockito.mock(LocalSessionFactoryBean.class);
	}

}

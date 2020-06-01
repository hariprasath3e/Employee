package com.employee.learning;

import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.employee.learning")
@PropertySource(value = { "classpath:application.properties" })
public class EmployeeSpringConfig {
	
    private static final Logger logger = LoggerFactory.getLogger(EmployeeSpringConfig.class);

    @Value("${mysql.driver}")
	private String dbConnector;
    
	@Value("${mysql.url}")
	private String dbUrl;
	
	@Value("${mysql.user}")
	private String dbUserName;
	
	@Value("${mysql.password}")
	private String dbPassword;
	
	@Value("${hibernate.show_sql}")
	private boolean showSql;
	
	@Value("${hibernate.hbm2ddl.auto}")
	private String hbmDdl;
	
	@Value("${hibernate.format_sql}")
	private boolean formatSql;
	
	@Value("${hibernate.dialect}")
	private String hbmdialect;
	
	@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dbConnector);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUserName);
        dataSource.setPassword(dbPassword);
        logger.info("DataSource " + dataSource);
        return dataSource;
    }
	
	@Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.employee.learning.model" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        logger.info("Session Factory " + sessionFactory);
        return sessionFactory;
     }
	
	private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", hbmdialect);
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.format_sql", formatSql);
        return properties;        
    }
	
	
}

package com.test.employee.learning;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.employee.learning.EmployeeSpringConfig;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EmployeeSpringConfig.class})
@Category(UnitTest.class)
public class EmployeeControllerTest {

	@Test
	public void testCase1() {
		assertTrue(true);
	}

	
}

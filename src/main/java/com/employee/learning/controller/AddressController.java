package com.employee.learning.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.employee.learning.model.Address;
import com.employee.learning.service.AddressService;

@RestController
@EnableWebMvc
@RequestMapping("/api/v1")
public class AddressController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping(value = "/employee/{id}/address", headers = "Accept=application/json")
	public ResponseEntity<?> getAllAddress(@PathVariable("id") int employeeId) {
		LOGGER.info("In all address");
		return addressService.getAllAddress(employeeId);
	}
	
	@GetMapping(value = "/employee/{id}/address/{addressId}", headers = "Accept=application/json")
	public ResponseEntity<?> getAddress(@PathVariable("id") int employeeId,@PathVariable("addressId") int addressId) {
		LOGGER.info("In Get Address");
		return addressService.getAddress(addressId, employeeId);
	}

	@PostMapping(value = "/employee/{id}/address", headers = "Accept=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addAddress(@PathVariable("id") int employeeId, @RequestBody Address address) {
		LOGGER.info("In Post Address");
		return addressService.addAddress(employeeId, address);
	}
	
	@DeleteMapping(value = "/employee/{id}/address/{addressId}", headers = "Accept=application/json")
	public ResponseEntity<?> deleteAddress(@PathVariable("id")  int employeeId, @PathVariable("addressId") int addressId) {
		LOGGER.info("In Delete Address");
		return addressService.deleteAddress(addressId, employeeId);
	}

}

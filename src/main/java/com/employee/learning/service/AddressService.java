package com.employee.learning.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.employee.learning.dao.AddressDao;
import com.employee.learning.model.Address;

@Service
public class AddressService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddressService.class);

	@Autowired
	private AddressDao addressDao;

	public ResponseEntity<?> getAllAddress(int employeeId) {
		LOGGER.info("get all address to DB..");
		return addressDao.getAllAddress(employeeId);
	}

	public ResponseEntity<?> getAddress(int addressId, int employeeId) {
		LOGGER.info("get address to DB..");
		Address address = addressDao.getAddress(addressId, employeeId);
		if (address == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception in get Address ");
		} else if (address.getEmployee().getId() == employeeId) {
			return ResponseEntity.ok().body(address);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address mapped to different employee..");
		}
	}

	public ResponseEntity<?> addAddress(int employeeId, Address address) {
		LOGGER.info("add address to DB..");
		Integer addressId = addressDao.addAddress(employeeId, address);
		if (addressId == null)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception in adding address");
		else if (addressId == 0)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found..");
		else
			return ResponseEntity.ok("Address added success");
	}

	public ResponseEntity<?> deleteAddress(int addressId, int employeeId) {
		LOGGER.info("delete address to DB..");
		Address address = addressDao.deleteAddress(addressId, employeeId);
		if (address == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception in get Address ");
		} else if (address.getId() != 0) {
			return ResponseEntity.ok().body("Address deleted success..");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address mapped to different employee..");
		}
	}

}

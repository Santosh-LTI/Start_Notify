package com.lti.amazon.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.lti.amazon.entity.Address;
import com.lti.amazon.service.AddressService;


import lombok.extern.log4j.Log4j2;



@RestController
@Log4j2
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AddressController {
	@Autowired
	private AddressService addressservice;
	
	@GetMapping("/getAllAddress")
	public ResponseEntity<List<Address>> getAllAddress() {
		log.info(" Get All Address received: ");
		List<Address> list = addressservice.getAllAddress();
		log.info("Address Recieved " + list);
		return new ResponseEntity<List<Address>>(list, HttpStatus.OK);
	}

	@GetMapping("/getAddress/{constraintNumber}")
	public ResponseEntity<Address> getAddress(
			@PathVariable("constraintNumber") long constraintNumber) {
		log.info(" Get Address By ID received: " + constraintNumber);
		Address addressById=addressservice.getAddress(constraintNumber);
		//BusinessRules transactionById = mainservice.getBusinessRule(constraintNumber);
		log.info("Transaction Recieved With Id" + constraintNumber + " received: " + addressById);
		return new ResponseEntity<Address>(addressById,HttpStatus.OK);
	}

	@PutMapping("/ModifyAddress/{id}")
	public ResponseEntity<Address> modifyAddress(@RequestBody Address address) {
		addressservice.ModifyAddress(address);
		return new ResponseEntity<Address>(address, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteAddress/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") long addressid) {
		addressservice.DeleteAddress(addressid);
        return new ResponseEntity<Void>(HttpStatus.OK);  	
    }
	
	@PostMapping("/AddAddress")
	public ResponseEntity<Void> saveAddress(@RequestBody Address address,
			UriComponentsBuilder builder) {
		log.info("Business Rule to add : " + address);
		boolean flag =addressservice.saveAddress(address);
		log.info("value of flag : " + flag);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/getAddress/{transactionId}").buildAndExpand(address).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	
	
}

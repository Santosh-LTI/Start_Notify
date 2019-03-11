package com.lti.amazon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lti.amazon.entity.Address;

@Service
public interface AddressService {
	boolean saveAddress(Address address);

	List<Address> getAllAddress();

	Address getAddress(long addressid);

	void ModifyAddress(Address address);

	void DeleteAddress(long addressid);
}

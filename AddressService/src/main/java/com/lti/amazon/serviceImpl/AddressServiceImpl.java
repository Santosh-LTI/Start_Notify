package com.lti.amazon.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.amazon.entity.Address;
import com.lti.amazon.repository.AddressRepository;
import com.lti.amazon.service.AddressService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class AddressServiceImpl implements AddressService{

	@Autowired
	AddressRepository addressrepository;
	
	
	@Override
	public boolean saveAddress(Address address) {
		Address persistDataVar = null;
		try {
			persistDataVar = addressrepository.findByAddressId(address.getAddress_id());
			log.info("In Service Impl finding Address" + persistDataVar);
					//(BusinessRules) businessrulesrepo.findByRuleId(businessRules.getRuleCode());
		} catch (NoSuchElementException ex) {
			log.info("Error in finding Address" + ex.getMessage());
		//	System.out.println("Error in finding Address" + ex.getMessage());
		}
		if (persistDataVar != null) {
			log.info("Inside if");
			return false;
		} else {
			System.out.println("Address details being saved in db");
			addressrepository.save(address);
			System.out.println("Address details saved in db");
			return true;
		}

	}

	@Override
	public List<Address> getAllAddress() {
		List<Address> list = new ArrayList<>();
		addressrepository.findAll().forEach(e -> list.add(e));
		return list;
		}

	@Override
	public Address getAddress(long addressid) {
		Address obj = addressrepository.findByAddressId(addressid);
		return obj;
	}

	@Override
	public void ModifyAddress(Address address) {
		addressrepository.save(address);
		
	}

	@Override
	public void DeleteAddress(long addressid) {
		addressrepository.deleteById(addressid);
		
	}

}

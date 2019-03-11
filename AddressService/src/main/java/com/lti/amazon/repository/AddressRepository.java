package com.lti.amazon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lti.amazon.entity.Address;

@RepositoryRestResource
public interface AddressRepository extends JpaRepository<Address, Long>{

	@Query(value="SELECT * FROM Address sd WHERE sd.address_id=?1",nativeQuery=true)
	Address findByAddressId(long addressid);
}

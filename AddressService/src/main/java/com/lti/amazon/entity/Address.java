package com.lti.amazon.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @Builder @Entity @Table(name="Address") @NoArgsConstructor @AllArgsConstructor @ToString @XmlRootElement
public class Address {
	@Id
	@Column
	private long address_id;
	@Column(name="employee_id", nullable=false)
	private long employee_id;
	@Column(name="addressType", nullable=false)
	private String addressType;
	@Column(name="country", nullable=false)
	private String country;
	@Column(name="state", nullable=false)
	private String state;
	@Column(name="city", nullable=false)
	private String city;
	@Column(name="address1", nullable=false)
	private String address1;
	@Column(name="address2", nullable=false)
	private String address2;
	@Column(name="address3", nullable=false)
	private String address3;
	@Column(name="address4", nullable=false)
	private String address4;
	@Column(name="postalCode", nullable=false)
	private String postalCode;
	@Column(name="county", nullable=false)
	private String county;
	@Column(name="effectiveDate")
	private Date effectiveDate;
	
}

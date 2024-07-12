package com.Paradise.Homeloan.main.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddressDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addId;
	private String houseNo;
	private String landmark;
	private String city;
	private String district;
	private long pincode;
	private String state;
	private String country;

}

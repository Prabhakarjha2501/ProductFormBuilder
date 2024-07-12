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
public class CustomerGuarantorDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int guarantorDetailsId;
	private String gurantorName;
	private String relation;
	private int age;
	private long mobileNumber;
	private String pancard;
	private String designation;
	

}

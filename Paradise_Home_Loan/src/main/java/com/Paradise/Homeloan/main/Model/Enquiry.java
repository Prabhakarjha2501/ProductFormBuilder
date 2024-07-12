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
public class Enquiry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Enquiry_sequense")
	private int enquiryId;
	private String customerFname;
	private String customerLname;
	private String customerEmail;
	private Long   customerMobileNumber;
	private String pancardNumber;
	
	private String cibilStatus;
	private Integer cibilScore;
	
	
}


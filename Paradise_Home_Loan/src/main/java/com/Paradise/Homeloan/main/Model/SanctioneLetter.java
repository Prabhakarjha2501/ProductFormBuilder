package com.Paradise.Homeloan.main.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanctioneLetter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sanctionId;
	private String sanctionDate;
	private String applicationName;
	private Double loanAmountSanctioned;
	private Double rateOfInterest;
	private Integer loanTenure;
	private Double monthlyEmiAmount;
	private String loanAmountWithInterest;
	private String termsAndCondition;
	@Lob
	private byte[] sanctionLetter;
	
		
}

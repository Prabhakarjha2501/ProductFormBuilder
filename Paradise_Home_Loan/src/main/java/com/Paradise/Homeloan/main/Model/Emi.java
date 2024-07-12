package com.Paradise.Homeloan.main.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@AllArgsConstructor
@NoArgsConstructor
public class Emi {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "Emi_Sequence")
	private int emiId;
	private String emiStatus;
	private double emiAmount;
	private int emiTenure;
	private String emiPaid;
	private int noOfEmi;
	private int emiNo;
	private String  emiReciveDate;
	private String nxtEmiDate;
	private Double toalRemaningAmt;
	
	@OneToOne(cascade = CascadeType.ALL)
	private SanctioneLetter letter;
}

package com.Paradise.Homeloan.main.Model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ledger {
	
	//
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ledgerId;
	
	private String ledgerCreatedDate;
	private Double totalPrincipalAmount;
	private Double payableAmountwithInterest;
	private Integer tenure;
	private Double monthlyEMI;
	private int noOfEmiRecive;
	private Double remainingAmount;
	private String nextEmiStartDate;
	private int totalNoOfEmi;
	private String currentMonthEmiStatus;
	private int emiNo;
	private String loanStatus;
	
	
}

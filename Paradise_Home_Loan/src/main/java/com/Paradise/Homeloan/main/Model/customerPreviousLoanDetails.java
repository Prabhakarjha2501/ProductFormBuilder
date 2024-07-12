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
public class customerPreviousLoanDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int previousLoanId;
	private Double loanAmount;
	private int loanTenure;
	private Double paidAmount;
	private Double remainingAmount;
	private int defaulterCount;
	private String bankName;
}

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
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Customer_Sequence")
	private int customerId;
	private String customerFname;
	private String customerMname;
	private String customerLname;
	private String customerDateOfBirth;
	private String customerGender; 
	
	private String customerEmail;
	private long customermobileNo;
	private String customerPanNo;
	private long customeradhaarNo;
	private String cibilStatus;
	private	Integer cibilScore;
	private String applicationStatus;
	private Double loanAmoutRequired;
	
	@OneToOne(cascade = CascadeType.ALL)
	private CustomerAddressDetails addressDetails; 
	@OneToOne(cascade = CascadeType.ALL)
	private CustomerProfessionalDetails profDetails;
	@OneToOne(cascade = CascadeType.ALL)
	private CustomerPropertyDetails propertyDetails;
	@OneToOne(cascade = CascadeType.ALL)
	private CustomerGuarantorDetails guarantorDetails;
	@OneToOne(cascade = CascadeType.ALL)
	private CustomerBuilderDetails builderDetails;
	@OneToOne(cascade = CascadeType.ALL)
	private customerPreviousLoanDetails loanDetails;
	@OneToOne(cascade = CascadeType.ALL)
	private CustomerDocumentsDetails documentsDetails;
	@OneToOne(cascade = CascadeType.ALL)
	private CustomerBankDetails bankDetails;
	@OneToOne (cascade = CascadeType.ALL)
	private LoanDisbursement disbursement;
	
	@OneToOne(cascade = CascadeType.ALL)
	private SanctioneLetter sanctionLetter;

	@OneToOne(cascade =CascadeType.ALL)
	private Ledger ledger;

	
	
}

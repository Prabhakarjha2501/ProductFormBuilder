package com.Paradise.Homeloan.main.ServiceI;

import java.util.Optional;

import com.Paradise.Homeloan.main.Model.Customer;
import com.Paradise.Homeloan.main.Model.LoanDisbursement;



public interface LoanDisbursementService {

	//public Customer updateloanDisbursement( Customer cst);

	public Optional<Customer> finddById(Integer cstid);

	public Customer updateloanDisbursement(Integer customerId, LoanDisbursement loandisbursement);

}

package com.Paradise.Homeloan.main.ServiceI;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.Paradise.Homeloan.main.Exception.PdfNotGenerated;
import com.Paradise.Homeloan.main.Model.Customer;
import com.Paradise.Homeloan.main.Model.SanctioneLetter;
import com.lowagie.text.DocumentException;

public interface SanctionService {

	Iterable<Customer> getCustomerbyStatus(String applicationStatus);

	Optional<Customer> findById(Integer cusid);

	Customer changeStatus(Customer customerDetails);

	Iterable<Customer> findByLoanStatus(String loanStatus);

	Customer generateSactionId(Integer customerId, SanctioneLetter sanctionLetter) throws PdfNotGenerated;

	
	

}

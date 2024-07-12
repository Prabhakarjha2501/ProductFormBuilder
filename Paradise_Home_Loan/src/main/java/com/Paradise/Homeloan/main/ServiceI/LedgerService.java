package com.Paradise.Homeloan.main.ServiceI;

import java.util.List;
import java.util.Optional;

import com.Paradise.Homeloan.main.Model.Customer;
import com.Paradise.Homeloan.main.Model.Ledger;

public interface LedgerService {

	Optional<Customer> findById(Integer customerId);

	Customer saveCustomerLedger(Customer customerDetails);

	Customer saveInstallment(Customer cust);

	List<Ledger> getAllLedgerDetails();

	List<Ledger> getLoansetteled();

	//Ledger getledgerdata(Integer ledgerId);

}

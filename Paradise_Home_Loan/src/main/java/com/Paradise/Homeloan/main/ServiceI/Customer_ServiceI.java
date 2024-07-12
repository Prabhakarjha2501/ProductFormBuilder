package com.Paradise.Homeloan.main.ServiceI;

import java.util.List;
import java.util.Optional;

import com.Paradise.Homeloan.main.Model.Customer;

public interface Customer_ServiceI {

	List<Customer> getCustomer();

	Customer setCustomer(Customer c);

	Optional<Customer> findById(Integer customerId);

	Iterable<Customer> getCustomerbyStatus(String applicationStatus);

	Customer updateCustomer(Customer singlecustomer);

	void deleteData(Integer customerId);

}

package com.Paradise.Homeloan.main.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Paradise.Homeloan.main.Model.Customer;
import com.Paradise.Homeloan.main.Repository.Customer_Repository;
import com.Paradise.Homeloan.main.ServiceI.Customer_ServiceI;

@Service
public class Customer_ServiceImpl implements Customer_ServiceI {
	@Autowired
	Customer_Repository cr;

	@Override
	public List<Customer> getCustomer() {
		
		return cr.findAll();
	}

	@Override
	public Customer setCustomer(Customer c) {
		
		return cr.save(c);
	}

	@Override
	public Optional<Customer> findById(Integer customerId) {
		
		return cr.findById(customerId);
	}

	@Override
	public Iterable<Customer> getCustomerbyStatus(String applicationStatus) {
		
		return cr.findAllByApplicationStatus(applicationStatus);
	}

	@Override
	public Customer updateCustomer(Customer cust) {
		
		return cr.save(cust);
	}

	@Override
	public void deleteData(Integer customerId) {
		cr.deleteById(customerId);
		
	}

}

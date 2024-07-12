package com.Paradise.Homeloan.main.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Paradise.Homeloan.main.Model.Customer;
import com.Paradise.Homeloan.main.Model.Ledger;

@Repository
public interface Customer_Repository extends JpaRepository<Customer, Integer>{

	Iterable<Customer> findAllByApplicationStatus(String applicationStatus);

	Optional<Customer> findByCustomerId(int customerId);

	//Ledger findByLedgerId(Integer ledgerId);

}

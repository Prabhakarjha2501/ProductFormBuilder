package com.Paradise.Homeloan.main.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Paradise.Homeloan.main.Model.Ledger;

public interface LedgerRepository extends JpaRepository<Ledger, Integer>{

	List<Ledger> findAllByLoanStatus(String string);

}

package com.Paradise.Homeloan.main.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Paradise.Homeloan.main.Model.Enquiry;

@Repository
public interface Enquiry_Repository extends JpaRepository<Enquiry, Integer> {

	
	

	

	List<Enquiry> findAllByCibilStatus(String cibilStatus);
	
		;
}

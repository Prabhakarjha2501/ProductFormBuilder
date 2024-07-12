package com.Paradise.Homeloan.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Paradise.Homeloan.main.Model.Emi;

@Repository
public interface Emi_Repository extends JpaRepository<Emi, Integer> {
	

}

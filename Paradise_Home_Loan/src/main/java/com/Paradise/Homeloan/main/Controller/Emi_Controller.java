package com.Paradise.Homeloan.main.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Paradise.Homeloan.main.Model.Emi;
import com.Paradise.Homeloan.main.ServiceI.Emi_ServiceI;

@CrossOrigin
@RestController
public class Emi_Controller {
	@Autowired
	Emi_ServiceI es;
	
	@GetMapping("/emi")
	public ResponseEntity<List<Emi>> getEmi(){
		return new ResponseEntity<List<Emi>>(es.getEmi(),HttpStatus.OK);
	}

	//@PostMapping("/emi")
}

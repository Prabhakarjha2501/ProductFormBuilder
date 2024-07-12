package com.Paradise.Homeloan.main.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.Paradise.Homeloan.main.Enum.CibilStatus;
import com.Paradise.Homeloan.main.Model.Enquiry;
import com.Paradise.Homeloan.main.ServiceI.Enquiry_ServiceI;

@CrossOrigin
@RestController
public class Enquiry_Controller {
	
	@Autowired
	Enquiry_ServiceI es;
	@Autowired
	RestTemplate rst;

	
	@GetMapping("/enquiry")
	public ResponseEntity<List<Enquiry>> getEnquiry() {
		 
		return new ResponseEntity<List<Enquiry>>( es.getEnqiry(),HttpStatus.OK);
	}
	
	@PostMapping("/enquiry")
	public ResponseEntity<Enquiry> setEnquiry(@RequestBody Enquiry e) {
				e.setCibilStatus(String.valueOf(CibilStatus.pending));
		return new  ResponseEntity<Enquiry>(es.setEnqiry(e),HttpStatus.CREATED);
	}
	
	
	@GetMapping("/enquiry/{cibilStatus}")
	public ResponseEntity<List<Enquiry>> getEnquiryStatus(@PathVariable("cibilStatus")
		String cibilStatus){
		
		return new ResponseEntity<List<Enquiry>>(es.getEnquiryStatus(cibilStatus),HttpStatus.OK);
	}
	
	

	@GetMapping("/updateCibil/{id}")
	public Enquiry generatCibil(@PathVariable int id)
	{
		Optional<Enquiry> enq = es.getSingleEnquiry(id);
		          
		        if(enq.isPresent())
		        {
		        	Enquiry enquiry=enq.get();
		       int cibil =	 rst.getForObject("http://localhost:8081/genrateCibil/"
		        	+enquiry.getPancardNumber(), Integer.class);  
		       if(cibil>=730) {
		    	   enquiry.setCibilScore(cibil);
		    	   enquiry.setCibilStatus(String.valueOf(CibilStatus.approved));
		    	   
		       }
		       
		           else {
		        	   enquiry.setCibilScore(cibil);
			    	   enquiry.setCibilStatus(String.valueOf(CibilStatus.rejected));
					
				}
		        return      es.setEnqiry(enquiry);
		  
		
	}
		        return   null;	
	}
	
}

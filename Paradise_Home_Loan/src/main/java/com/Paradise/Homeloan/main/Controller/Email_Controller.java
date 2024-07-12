package com.Paradise.Homeloan.main.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Paradise.Homeloan.main.Model.Customer;
import com.Paradise.Homeloan.main.Model.Email;
import com.Paradise.Homeloan.main.Model.Enquiry;
import com.Paradise.Homeloan.main.ServiceI.Customer_ServiceI;
import com.Paradise.Homeloan.main.ServiceI.Email_ServiceI;

@CrossOrigin
@RestController
public class Email_Controller {
	@Autowired
	Email_ServiceI es;
	@Autowired
	Email email;
	@Autowired
	Customer_ServiceI cs;
	
	@Value("${spring.mail.username}")
	String emaifrom;
	
	@PostMapping("/sendmail")
	public ResponseEntity<Enquiry> sendMail(@RequestBody Enquiry enq)
	{
		System.out.println("cibil status "+enq.getCibilStatus());
		if(enq.getCibilStatus().equals("approved"))
		{
		email.setFormEmail(emaifrom);
        email.setToEmail(enq.getCustomerEmail());
		email.setSubject("Regarding Car Loan For Documentation of Applicant name: "+ enq.getCustomerFname() +" "+ enq.getCustomerLname());
		email.setTextMsg("your cibil is Approved and You are Eligible\n"
      		+ "For Further Process."
      		+ "\n We are happy to inform you that your Home Loan request has been approved and is cureently being processed.\n"
      		+ "Further, we inform you that we have sent an email containing attached documents.\n"
      		+ "Also we have sent you the terms and conditions of the loans sanctioned. \n"
      		+ "We would like to schedule your meeting with the finance officer of the company for any further information and clarifications that you might wish to know. \n\n"
      		+ "We are happy to be doing business with you. \n\n"
      		+ "List of Documents Required :- \n\n"
      		+ "1.Aadhar Card \n"
      		+ "2.Pan Card \n"
      		+ "3.Income Tax Return of Last Two Years \n"
      		+ "4.Salary Slips of Last Three Months \n"
      		+ "5.Passport Size Photograph \n"
      		+ "6.Bank Passbook Copy \n"
      		+ "\n \n Thanking You. \n"
      		+ "Mr.Basavraj Bhorshetti\n"
      		+ "Branch Manager \n"
      		+ "Pradise Finance Ltd. \n Karvenagar \n"
      		+ "Pune, Maharashtra \n India-411052\n"
      		+ "\n"
      		+ "Thank You For Banking With Us \n\n"
      		+ "Pradise Finance Ltd.....!!!!");
		es.sendMail(email);
          return new ResponseEntity<Enquiry>(HttpStatus.OK);
		}
		else 
		{
			email.setFormEmail(emaifrom);
	        email.setToEmail(enq.getCustomerEmail());
			email.setSubject("Regarding Car Loan For Documentation of Applicant name: "+ enq.getCustomerFname() +" "+ enq.getCustomerLname());
			email.setTextMsg("your cibil is Rejected and You are Not Eligible\n"
	        		+ "For Further Process."
	        		+ "\n We are Not Happy to inform you that your Home Loan request has been Rejected and is currently being Not Processed.\n"

	        		+ "\n \n Thanking You. \n"
	        		+ "Mr.Basavraj Bhorshetti \n"
	        		+ "Branch Manager \n"
	        		+ "Pradise Finance Ltd. \n Karvenagar \n"
	        		+ "Pune, Maharashtra \n India-411052\n"
	        		+ "\n"
	        		+ "Thank You For Banking With Us \n\n"
	        		+ "Pradise Finance Ltd.....!!!!");
	        
			es.sendMail(email);
	        return new ResponseEntity<Enquiry>(HttpStatus.OK);
		}
	
	}
	
	
	@GetMapping("/sendSantionLetterMail/{customerId}")
	public ResponseEntity<Customer> sendSanctionLetterMail(@PathVariable("customerId") Integer customerId)
	{
		System.out.println("Mail sending started");
		Optional<Customer> customerdetail = cs.findById(customerId);
		Customer customerDetails = customerdetail.get();
		if(customerdetail.isPresent())
		{
			es.sendSantionLetterMail(customerDetails);
		}
		else
		{
			//throw new CustomerNotFound();
		}
		return new ResponseEntity<Customer>(HttpStatus.OK);
	}
}

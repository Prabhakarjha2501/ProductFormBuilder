package com.Paradise.Homeloan.main.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Paradise.Homeloan.main.Enum.BaseResponse;
import com.Paradise.Homeloan.main.Enum.CustomerLoanStatus;
import com.Paradise.Homeloan.main.Exception.CustomerNotFound;
import com.Paradise.Homeloan.main.Exception.PdfNotGenerated;
import com.Paradise.Homeloan.main.Model.Customer;
import com.Paradise.Homeloan.main.Model.Email;
import com.Paradise.Homeloan.main.Model.SanctioneLetter;
import com.Paradise.Homeloan.main.ServiceI.SanctionService;


@CrossOrigin("*")
@RestController
public class Sanction_Controller {

	@Autowired
	SanctionService ss;
	
	@Value("${spring.mail.username}")
	private String fromEmail;
	
	@GetMapping("/getCustomer/{applicationStatus}")	//get customer by loan status
	public ResponseEntity<Iterable<Customer>> getCustomerByStatus(
			@PathVariable("applicationStatus") String applicationStatus) {
		Customer cstd = null;
		Iterable<Customer> cst = ss.getCustomerbyStatus(applicationStatus);
		for (Customer cstds : cst) {
			if (cstds != null) {
				cstd = cstds;
			}
		}
		if (cstd != null) {
	
			return new ResponseEntity<Iterable<Customer>>(cst, HttpStatus.OK);
		} else {
			throw new CustomerNotFound();
		}
	}

	
//	@PutMapping(value = "/updateCustomer/{cstid}")	//update customer by id
//	public ResponseEntity<BaseResponse<CustomerDetails>> updateCustomer(@PathVariable Integer cstid,
//			@RequestPart("allData") String allData) throws IOException {
//		ObjectMapper om = new ObjectMapper();
//		if(allData.isEmpty()) {
//			throw new CustomerNotFound();
//		}else {
//			CustomerDetails csd = om.readValue(allData, CustomerDetails.class);
//
//			csd.setCustomerLoanStatus(String.valueOf(CustomerLoanStatus.SanctionLetterGenerated));
//			CustomerDetails customerdetails = ss.updateCustomer(cstid, csd);
//			BaseResponse br = new BaseResponse<>(201, "Data Successfully Updated..", customerdetails);
//			return new ResponseEntity<BaseResponse<CustomerDetails>>(br, HttpStatus.ACCEPTED);
//		}
//	}
	
	@PutMapping("/generatePdf/{customerId}")
	public ResponseEntity<BaseResponse<Customer>> updateSactionLetter(@PathVariable("customerId") Integer customerId, 
			@RequestBody SanctioneLetter sanctionLetter)throws PdfNotGenerated {
	Email email = new Email();
		Customer customerdetail = new Customer();
		email.setFormEmail(fromEmail);
			Customer customerdetails = ss.generateSactionId(customerId,sanctionLetter);
			
			BaseResponse br = new BaseResponse<>(200,"Sanction Letter Generated", customerdetails);
			return new ResponseEntity<BaseResponse<Customer>>(br, HttpStatus.OK);

	}
	
	 @PutMapping("/sanctionUpdate/{cusid}")
	  public ResponseEntity<BaseResponse<Customer>> sanctionUpdate(@PathVariable("cusid") 
	  Integer cusid,@RequestBody String loanStatus){
		Optional<Customer> findById = ss.findById(cusid);
		Customer customerDetails = findById.get();
		
		if(findById.isPresent()) {
			if(loanStatus.equals("Accepted")) {
				customerDetails.setApplicationStatus(String.valueOf(CustomerLoanStatus.SanctionLetterApproved));
			}
			else if(loanStatus.equals("Rejected")) 
			{
				customerDetails.setApplicationStatus(String.valueOf(CustomerLoanStatus.SanctionLetterRejected));
			}
			  
		}
		else 
		{
			throw new CustomerNotFound();
		}
		Customer customerData = ss.changeStatus(customerDetails);
		BaseResponse baseResponse=new BaseResponse(200,"Sanction Letter status Updated",customerData);
		return new ResponseEntity<BaseResponse<Customer>>(baseResponse,HttpStatus.OK);
	}
	
	@GetMapping("/sanctionLetterStatus/{loanStatus}")
	public ResponseEntity<BaseResponse<Iterable<Customer>>> sanctionletterstatus(@PathVariable("loanStatus") String loanStatus)
	{
		Iterable<Customer> findByLoanStatus = ss.findByLoanStatus(loanStatus);
		BaseResponse baseResponse=new BaseResponse<>(200,"All Data",findByLoanStatus);
		return new ResponseEntity<BaseResponse<Iterable<Customer>>>(baseResponse,HttpStatus.OK);
	}

	
}
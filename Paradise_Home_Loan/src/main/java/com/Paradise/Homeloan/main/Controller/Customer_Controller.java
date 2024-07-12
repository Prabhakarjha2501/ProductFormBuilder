package com.Paradise.Homeloan.main.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.mail.Multipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Paradise.Homeloan.main.Enum.BaseResponse;
import com.Paradise.Homeloan.main.Enum.CibilStatus;
import com.Paradise.Homeloan.main.Enum.CustomerLoanStatus;
import com.Paradise.Homeloan.main.Exception.CustomerNotFound;
import com.Paradise.Homeloan.main.Model.Customer;
import com.Paradise.Homeloan.main.Model.CustomerDocumentsDetails;
import com.Paradise.Homeloan.main.ServiceI.Customer_ServiceI;

import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
public class Customer_Controller {
	@Autowired
	Customer_ServiceI cs;
	
	@GetMapping(value = "/customer") //get all customer
	public ResponseEntity<List<Customer>> getCustomer(){
		return new ResponseEntity<List<Customer>>(cs.getCustomer(),HttpStatus.OK);
	}

	/**
	 * @param cust
	 * @param pan
	 * @param photo
	 * @param adhar
	 * @param salaryslip
	 * @param banksmt
	 * @param addrproof
	 * @return
	 * @throws ClassCastException
	 * @throws IOException
	 */
	
	//save the customer data (cibil status-aproved)
	@PostMapping(value = "/customer", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,"application/json"})
	public ResponseEntity<Customer> setCustomer(@RequestPart("cust") String cust,
			@RequestPart("pan") MultipartFile pan,
			@RequestPart("photo") MultipartFile photo,
			@RequestPart("adhar") MultipartFile adhar,
			@RequestPart("salaryslip") MultipartFile salaryslip,
			@RequestPart("banksmt") MultipartFile banksmt,
			@RequestPart("addproof") MultipartFile addproof) throws ClassCastException, IOException{
		
		
		CustomerDocumentsDetails d= new CustomerDocumentsDetails();
		d.setPancard(pan.getBytes());
		d.setPhoto(photo.getBytes());
		d.setAdharcard(adhar.getBytes());
		d.setSalaryslip(salaryslip.getBytes());
		d.setBanksmt(banksmt.getBytes());
		d.setAddproof(addproof.getBytes());
		
	ObjectMapper om = new ObjectMapper();
			Customer c= om.readValue(cust, Customer.class);
			
			c.setApplicationStatus(String.valueOf(CibilStatus.pending));
				c.setDocumentsDetails(d);
				
				
		return new ResponseEntity<Customer>(cs.setCustomer(c),HttpStatus.OK);
	}
	
	
	@GetMapping("/customerbystatus/{applicationStatus}")	//get customer by loan status [approved - rejected]
	public ResponseEntity<BaseResponse<Iterable<Customer>>> getCustomerByStatus(
			@PathVariable("applicationStatus") String applicationStatus) {
		
		Iterable<Customer> cst = cs.getCustomerbyStatus(applicationStatus);
		

	
	
		BaseResponse<Iterable<Customer>> br = new BaseResponse<>(200, "All Data Successfully get..", cst);
		return new ResponseEntity<BaseResponse<Iterable<Customer>>>(br, HttpStatus.OK);
	
	}
	@GetMapping("/getsingleCutomer/{customerId}") //get single customer by id
	public ResponseEntity<Optional<Customer>> getSingleCustomer(@PathVariable Integer customerId) {
		Optional<Customer> singleCustomer = cs.findById(customerId);
		if (singleCustomer.isPresent()) {
			
			return new ResponseEntity<Optional<Customer>>(singleCustomer, HttpStatus.OK);

		} else {
			throw new CustomerNotFound();
		}
	}
	
	@PutMapping(value = "/customer/{customerId}")	//update customer documentverfiction
	public ResponseEntity<Customer> updateCustomer(@RequestBody String loanstatus,
            @PathVariable("customerId") Integer customerId) throws IOException
{

		Optional<Customer> customerdetails = cs.findById(customerId);

		if (customerdetails.isPresent())
{
Customer singlecustomer = customerdetails.get();	
if(loanstatus.equals("documentverfied")) 
{
singlecustomer.setApplicationStatus(String.valueOf(CustomerLoanStatus.DocumentVerified));
Customer cd = cs.updateCustomer(singlecustomer);

return new ResponseEntity<Customer>(cd, HttpStatus.OK);
}
else if(loanstatus.equals("documentrejected"))
{
singlecustomer.setApplicationStatus(String.valueOf(CustomerLoanStatus.DocumentRejected));
Customer cd2 = cs.updateCustomer(singlecustomer);

return new ResponseEntity<Customer>(cd2,HttpStatus.OK);	
}
}
else 
{
throw new CustomerNotFound();
}
return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);

}

@DeleteMapping("/deleteCustomer/{customerId}")	//delete customer by id
public ResponseEntity<Optional<Customer>> deleteCustomer(@PathVariable Integer customerId) {

 Optional<Customer> customerdetail = cs.findById(customerId);
if (customerdetail.isPresent()) {
cs.deleteData(customerId);

return new ResponseEntity<Optional<Customer>>(customerdetail,HttpStatus.OK);

} else {

throw new CustomerNotFound();
}
}
}

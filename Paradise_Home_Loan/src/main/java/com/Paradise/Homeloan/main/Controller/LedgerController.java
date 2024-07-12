package com.Paradise.Homeloan.main.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Paradise.Homeloan.main.Enum.BaseResponse;
import com.Paradise.Homeloan.main.Exception.CustomerNotFound;
import com.Paradise.Homeloan.main.Model.Customer;
import com.Paradise.Homeloan.main.Model.Ledger;
import com.Paradise.Homeloan.main.ServiceI.LedgerService;

@CrossOrigin
@RestController
public class LedgerController {

	
	
	@Autowired
	LedgerService ls;
	
//	@PutMapping("/updateLedger/{customerId}")
//	public ResponseEntity<BaseResponse<Customer>> updateLedger(@PathVariable("customerId") Integer customerId,
//			@RequestBody Ledger ledger){
//		Optional<Customer> findById = ls.findById(customerId);
//		Customer customerDetails = findById.get();
//		if(findById.isPresent()) {
//			
//			//customerDetails.getLedger().setLedgerId(ledger.getLedgerId());
//			customerDetails.getLedger().setTotalPrincipalAmount(ledger.getTotalPrincipalAmount());
//			customerDetails.getLedger().setMonthlyEMI(ledger.getMonthlyEMI());
//			customerDetails.getLedger().setTenure(ledger.getTenure());
//			customerDetails.getLedger().setLedgerCreatedDate(ledger.getLedgerCreatedDate());
//			customerDetails.getLedger().setNextEmiStartDate(ledger.getNextEmiStartDate());
//			customerDetails.getLedger().setPreviousEmiStatus(ledger.getPreviousEmiStatus());
//			customerDetails.getLedger().setCurrentMonthEmiStatus(ledger.getCurrentMonthEmiStatus());
//			customerDetails.getLedger().setLoanStatus("Emi start");
//			customerDetails.getLedger().setRemainingAmount(ledger.getRemainingAmount());
//			customerDetails.getLedger().setAmountPaidtillDate(ledger.getAmountPaidtillDate());
//			customerDetails.getLedger().setPayableAmountwithInterest(ledger.getPayableAmountwithInterest());
//			customerDetails.getLedger().setLoanEndDate(ledger.getLoanEndDate());
//		}else {
//			throw new CustomerNotFound();
//		}
//		Customer saveCustomerLedger = ls.saveCustomerLedger(customerDetails);
//		BaseResponse baseResponse=new BaseResponse(200,"Ledger Info Updated",saveCustomerLedger);
//		return new ResponseEntity<BaseResponse<Customer>>(baseResponse,HttpStatus.OK);
//	}
//	
	@PutMapping("/saveEmi/{customerId}")
	public ResponseEntity<Customer> saveInstallment (@RequestBody Ledger ledger, 
			@PathVariable int customerId )
	{
		
		Optional<Customer> optional = ls.findById(customerId);
		
		Customer cust = optional.get();
		
		cust.setLedger(ledger);
		
		Customer applicantDetails2 = ls.saveInstallment(cust);
		
		return new ResponseEntity<Customer>(applicantDetails2, HttpStatus.ACCEPTED);
		
		
	}
	
	@GetMapping("/getAllInstallmentDetails")
	public ResponseEntity<List<Ledger>> getAllInstallmentDetails()
	{
		
		List<Ledger> list = ls.getAllLedgerDetails();
		
		return new ResponseEntity<List<Ledger>>(list, HttpStatus.OK);
		
	}
	
	
	  @GetMapping("/getloanSetteled")
	  public ResponseEntity<List<Ledger>> getLedgerDefaulter() {
	  
	  List<Ledger> list = ls.getLoansetteled();
	  
	  return new ResponseEntity<List<Ledger>>(list,HttpStatus.OK);
	  
	  }
	 
	   
	   

	   
}

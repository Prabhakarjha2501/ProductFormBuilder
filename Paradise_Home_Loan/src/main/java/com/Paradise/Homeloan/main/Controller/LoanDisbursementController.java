package com.Paradise.Homeloan.main.Controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.Paradise.Homeloan.main.Enum.BaseResponse;
import com.Paradise.Homeloan.main.Enum.CustomerLoanStatus;
import com.Paradise.Homeloan.main.Exception.CustomerNotFound;
import com.Paradise.Homeloan.main.Model.Customer;
import com.Paradise.Homeloan.main.Model.LoanDisbursement;
import com.Paradise.Homeloan.main.ServiceI.LoanDisbursementService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@CrossOrigin
@RestController
public class LoanDisbursementController {
	@Autowired
	LoanDisbursementService ls;
	

	
	
	
	@PutMapping("/loanDisbursementupdate/{customerId}")
	public ResponseEntity<BaseResponse<Customer>> updateLoan(@RequestBody LoanDisbursement loandisbursement,
			@PathVariable("customerId") Integer customerId) throws IOException
	{ 
//		Optional<Customer> customer = ls.finddById(customerId);
//
//		if(customer.isPresent()) {	
//			Customer customerdata = customer.get();
//			 customerdata.setApplicationStatus(String.valueOf(CustomerLoanStatus.LoanDisbursed));
//			// customerdata.getDisbursement().setAmountPaidDate(loandisbursement.getAmountPaidDate());
//			    customerdata.getDisbursement().setAmountPaidDate(loandisbursement.getAmountPaidDate());
//			    customerdata.getDisbursement().setTotalLoanSanctionedAmount(loandisbursement.getTotalLoanSanctionedAmount());
//				customerdata.getDisbursement().setTransferedAmount(loandisbursement.getTransferedAmount());
//				
//				customerdata.getDisbursement().setPaymentStatus(loandisbursement.getPaymentStatus());
//				customerdata.getDisbursement().setDealerBankAccountNumber(loandisbursement.getDealerBankAccountNumber());
//				customerdata.getDisbursement().setDealerBankName(loandisbursement.getDealerBankName());
//				customerdata.getDisbursement().setDealerBankIfscNumber(loandisbursement.getDealerBankIfscNumber());
//				
				Customer customerdetails=ls.updateloanDisbursement(customerId,loandisbursement);
				BaseResponse br= new BaseResponse<>(200,"Loan Disbursed successfully !!!!",customerdetails);
				return new ResponseEntity<BaseResponse<Customer>>(br,HttpStatus.OK);
//			
//		}else {		
//			throw new CustomerNotFound();
//		}		
	}
}

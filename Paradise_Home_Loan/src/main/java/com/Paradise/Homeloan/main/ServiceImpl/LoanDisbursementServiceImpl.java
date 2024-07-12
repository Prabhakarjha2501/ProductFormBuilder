package com.Paradise.Homeloan.main.ServiceImpl;

import java.util.Optional;

import javax.swing.CellEditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Paradise.Homeloan.main.Enum.CustomerLoanStatus;
import com.Paradise.Homeloan.main.Exception.CustomerNotFound;
import com.Paradise.Homeloan.main.Model.Customer;
import com.Paradise.Homeloan.main.Model.LoanDisbursement;
import com.Paradise.Homeloan.main.Repository.Customer_Repository;
import com.Paradise.Homeloan.main.ServiceI.LoanDisbursementService;

@Service
public class LoanDisbursementServiceImpl implements LoanDisbursementService {
	
	@Autowired
	Customer_Repository cr;
	
//	@Override
//	public Customer updateloanDisbursement( Customer cst)
//	{
//
//		return cr.save(cst);
//	}
	
	@Override
	public Optional<Customer> finddById(Integer cstid) 
	{
	
		  return cr.findById(cstid);
		
	}

	@Override
	public Customer updateloanDisbursement(Integer customerId, LoanDisbursement loandisbursement) {
					Optional<Customer> op = cr.findById(customerId);
					
		if(op.isPresent()) {
			Customer customerdata = op.get();
			 customerdata.setApplicationStatus(String.valueOf(CustomerLoanStatus.LoanDisbursed));
				    customerdata.getDisbursement().setAmountPaidDate(loandisbursement.getAmountPaidDate());
				    customerdata.getDisbursement().setAmountPaidDate(loandisbursement.getAmountPaidDate());
				    customerdata.getDisbursement().setTotalLoanSanctionedAmount(loandisbursement.getTotalLoanSanctionedAmount());
					customerdata.getDisbursement().setAmountwithIntrest(loandisbursement.getAmountwithIntrest());					
					customerdata.getDisbursement().setPaymentStatus(loandisbursement.getPaymentStatus());
					customerdata.getDisbursement().setDealerBankAccountNumber(loandisbursement.getDealerBankAccountNumber());
					customerdata.getDisbursement().setDealerBankName(loandisbursement.getDealerBankName());
					customerdata.getDisbursement().setDealerBankIfscNumber(loandisbursement.getDealerBankIfscNumber());
					return cr.save(customerdata);
		}
		
		//		Optional<Customer> customer = cr.finddById(customerId);
//		Customer customerdata = customer.get();
//		if(customer.isPresent()) {	
//			
		else {
			throw new CustomerNotFound();
		}
		
	}

}

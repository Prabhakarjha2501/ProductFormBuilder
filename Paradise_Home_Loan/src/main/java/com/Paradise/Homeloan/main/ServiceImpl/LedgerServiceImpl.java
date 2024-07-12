package com.Paradise.Homeloan.main.ServiceImpl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Paradise.Homeloan.main.Model.Customer;
import com.Paradise.Homeloan.main.Model.Ledger;
import com.Paradise.Homeloan.main.Repository.Customer_Repository;
import com.Paradise.Homeloan.main.Repository.LedgerRepository;
import com.Paradise.Homeloan.main.ServiceI.LedgerService;

@Service
public class LedgerServiceImpl implements LedgerService {

	@Autowired
	Customer_Repository cr;
	@Autowired
	LedgerRepository lr;
	@Override
	public Optional<Customer> findById(Integer cusid) {
		return cr.findById(cusid);
	}

	@Override
	public Customer saveCustomerLedger(Customer customerDetails) {
		
		return cr.save(customerDetails);
	}

	@Override
	public Customer saveInstallment(Customer cust) {
		
		Integer totalNumberOfEmi = (int) (cust.getSanctionLetter().getLoanTenure()*12);
		
		Optional<Customer> applicant = cr.findByCustomerId(cust.getCustomerId());
						
		Optional<Ledger> installment = lr.findById( cust.getLedger().getLedgerId());
		
		Double totalPayableAmountWithIntrest = (double) ((applicant.get().getSanctionLetter().getMonthlyEmiAmount())*(totalNumberOfEmi));
				
		
		
			
			if(installment.isEmpty())
		{
				
				System.out.println("in IF");
				cust.getLedger().setEmiNo(1);
				
				int numberOfEmiRecived = (cust.getLedger().getEmiNo());
				int numberOfEmisRemaining = (totalNumberOfEmi) - (cust.getLedger().getEmiNo());
				Double totalRemainingAmount = ((totalPayableAmountWithIntrest)-(cust.getSanctionLetter().getMonthlyEmiAmount()));
				
				cust.getLedger().setTotalNoOfEmi(totalNumberOfEmi);
				cust.getLedger().setNoOfEmiRecive(numberOfEmiRecived);
				cust.getLedger().setRemainingAmount(totalRemainingAmount);
				cust.getLedger().setLoanStatus("Active");
				
		}
			
			else
		{
				System.out.println("In Else");
				
				
              
				
				int numberOfInstallment = (installment.get().getEmiNo())+1;
				int numberOfEmiRecived = numberOfInstallment;
				int numberOfEmisRemaining = ((totalNumberOfEmi) - (numberOfInstallment));
				
				Double totalRemainingAmount =(installment.get().getRemainingAmount())-(cust.getSanctionLetter().getMonthlyEmiAmount());
				cust.getLedger().setLedgerCreatedDate(cust.getLedger().getLedgerCreatedDate());
				cust.getLedger().setTotalPrincipalAmount(cust.getLedger().getTotalPrincipalAmount());
				cust.getLedger().setPayableAmountwithInterest(cust.getLedger().getPayableAmountwithInterest());
				cust.getLedger().setTenure(cust.getLedger().getTenure());
				
				cust.getLedger().setMonthlyEMI(cust.getLedger().getMonthlyEMI());
				cust.getLedger().setNoOfEmiRecive(numberOfEmiRecived);
				cust.getLedger().setNextEmiStartDate(cust.getLedger().getNextEmiStartDate()+(Calendar.MONTH)+1);
				cust.getLedger().setCurrentMonthEmiStatus("paid");
				cust.getLedger().setEmiNo(numberOfInstallment);
				cust.getLedger().setTotalNoOfEmi(totalNumberOfEmi);
				cust.getLedger().setRemainingAmount(totalRemainingAmount);
				cust.getLedger().setLoanStatus("Active");
				
				if(totalRemainingAmount == 0)
			{
					
				if(numberOfInstallment == totalNumberOfEmi)
					{
					
					cust.getLedger().setLoanStatus("LoanSettedled");
					
					}
				else
					{
					
					cust.getLedger().setLoanStatus("Active");
					
					}
					
					
			}
				else
			{
					
					cust.getLedger().setLoanStatus("Active");
					
			}
						
		}
			
			return cr.save(cust);
		
		
	}

	@Override
	public List<Ledger> getAllLedgerDetails() {
		
		return lr.findAll();
	}

	@Override
	public List<Ledger> getLoansetteled() {
		
		return lr.findAllByLoanStatus("LoanSettedled");
	}


	

}

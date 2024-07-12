package com.Paradise.Homeloan.main.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Paradise.Homeloan.main.Model.Enquiry;
import com.Paradise.Homeloan.main.Repository.Enquiry_Repository;
import com.Paradise.Homeloan.main.ServiceI.Enquiry_ServiceI;

@Service
public class Enquiry_ServiceImpl implements Enquiry_ServiceI{
	@Autowired
	Enquiry_Repository er;

	@Override
	public List<Enquiry> getEnqiry() {
		
		return er.findAll();
	}

	@Override
	public Enquiry setEnqiry(Enquiry e) {
			return er.save(e);
	}
//	@Override
//	public Integer genrateCibil() {
//		//int score = (int)(Math.random()*(max-min+1)+min); 
//		
//		int score = (int)(Math.random()*(800-640+1)+640); 
//		return score;
//		
//		
//	}

		
		
		
		
		
		
//		Optional<Enquiry> op = er.findById(enquiryId);
//		if(op.isPresent()) {
//			Enquiry enquiry = op.get();
//					enquiry.setEnquiryId(e.getEnquiryId());
//					enquiry.setCustomerFname(e.getCustomerFname());
//					enquiry.setCustomerLname(e.getCustomerLname());
//					enquiry.setCustomerEmail(e.getCustomerEmail());
//					enquiry.setCustomerMobileNumber(e.getCustomerMobileNumber());
//					enquiry.setPancardNumber(e.getPancardNumber());
//					enquiry.setCibilScore(e.getCibilScore());
//					enquiry.setCibilStatus(e.getCibilStatus());
//					int score = (int)(Math.random()*(800-640+1)+640);  
//						System.out.println(score); 
//						if(score>750) {
//							enquiry.getCibil().setCibilScore(score);
//							enquiry.getCibil().setCibilStatus(true);
//						}
//						else {
//							enquiry.getCibil().setCibilScore(score);
//							enquiry.getCibil().setCibilStatus(false);
//						}
//						return er.save(enquiry);
//		}
//		else {
//			throw new RuntimeException();
//		}
 		
	


	@Override
	public List<Enquiry> getEnquiryStatus(String cibilStatus) {
		
		return er.findAllByCibilStatus(cibilStatus);
	}

 @Override
       public Optional<Enquiry> getSingleEnquiry(int id) {
	
	     return er.findById(id);
}

	
	



}

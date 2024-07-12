package com.Paradise.Homeloan.main.ServiceI;

import java.util.List;
import java.util.Optional;

import com.Paradise.Homeloan.main.Model.Enquiry;

public interface Enquiry_ServiceI {

	List<Enquiry> getEnqiry();

	Enquiry setEnqiry(Enquiry e);

	
	
	List<Enquiry> getEnquiryStatus(String cibilStatus);

	Optional<Enquiry> getSingleEnquiry(int id);

	

}

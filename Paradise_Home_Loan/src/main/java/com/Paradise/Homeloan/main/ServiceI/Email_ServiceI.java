package com.Paradise.Homeloan.main.ServiceI;

import com.Paradise.Homeloan.main.Model.Customer;
import com.Paradise.Homeloan.main.Model.Email;
import com.Paradise.Homeloan.main.Model.Enquiry;

public interface Email_ServiceI {

	void sendMail(Email email);

	void sendSantionLetterMail(Customer customerDetails);

}

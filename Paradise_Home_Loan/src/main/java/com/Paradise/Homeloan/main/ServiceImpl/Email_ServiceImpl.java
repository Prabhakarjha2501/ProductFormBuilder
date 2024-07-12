package com.Paradise.Homeloan.main.ServiceImpl;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.Paradise.Homeloan.main.Model.Customer;
import com.Paradise.Homeloan.main.Model.Email;
import com.Paradise.Homeloan.main.ServiceI.Email_ServiceI;

@Service
public class Email_ServiceImpl implements Email_ServiceI {

	@Autowired
	JavaMailSender sender;
	@Override
	public void sendMail(Email e) {
		try {
		SimpleMailMessage message=new SimpleMailMessage();
	      message.setTo(e.getToEmail());
	      message.setFrom(e.getFormEmail());
	      message.setSubject(e.getSubject());
	      message.setText(e.getTextMsg());
	      
	      sender.send(message);
	} catch (MailException e1) {
		System.out.println("Email Failed to send");
		e1.printStackTrace();
	}	
	}

	@Value("${spring.mail.username}")
	String emaifrom;
	
	@Override
	public void sendSantionLetterMail(Customer customerDetails) {
     MimeMessage mimemessage = sender.createMimeMessage();
		
		byte[] sanctionLetter = customerDetails.getSanctionLetter().getSanctionLetter();

		try {
			MimeMessageHelper mimemessageHelper = new MimeMessageHelper(mimemessage, true);
			mimemessageHelper.setFrom(emaifrom);
			mimemessageHelper.setTo(customerDetails.getCustomerEmail());
			mimemessageHelper.setSubject("Pradice Finance Ltd. Sanction Letter");
			String text = "Dear " + customerDetails.getCustomerLname()+" " + customerDetails.getCustomerFname() 
					+ ",\n" + "\n"
					+ "This letter is to inform you that we have reviewed your request for a credit loan . We are pleased to offer you a credit loan of "
					+ customerDetails.getSanctionLetter().getLoanAmountSanctioned() + " for "
					+ customerDetails.getSanctionLetter().getLoanTenure() + ".\n" + "\n"
					+ "We are confident that you will manage your credit loan responsibly, and we look forward to your continued business.\n"
					+ "\n"
					+ "Should you have any questions about your credit loan, please do not hesitate to contact us.\n"
					+ "\n" + "Thank you for your interest in our services.";

			mimemessageHelper.setText(text);

			mimemessageHelper.addAttachment("loanSanctionLetter.pdf", new ByteArrayResource(sanctionLetter));
			sender.send(mimemessage);

		} catch (Exception e) {
			System.out.println("Email Failed to Send!!!!!!");
			e.printStackTrace();
		}

		
		
	}
	

}

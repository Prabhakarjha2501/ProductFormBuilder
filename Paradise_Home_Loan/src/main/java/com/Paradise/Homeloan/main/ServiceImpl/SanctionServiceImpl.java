package com.Paradise.Homeloan.main.ServiceImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.Paradise.Homeloan.main.Enum.CustomerLoanStatus;
import com.Paradise.Homeloan.main.Exception.CustomerNotFound;
import com.Paradise.Homeloan.main.Exception.PdfNotGenerated;
import com.Paradise.Homeloan.main.Model.Customer;
import com.Paradise.Homeloan.main.Model.SanctioneLetter;
import com.Paradise.Homeloan.main.Repository.Customer_Repository;
import com.Paradise.Homeloan.main.ServiceI.SanctionService;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


@Service
public class SanctionServiceImpl implements SanctionService {

	@Autowired
	Customer_Repository cr;


	@Value("${spring.mail.username}")
	private String fromEmail;

	Logger logger = LoggerFactory.getLogger(SanctionServiceImpl.class);

	@Override
	public Iterable<Customer> getCustomerbyStatus(String custloanstatus) {
		return  cr.findAllByApplicationStatus(custloanstatus);
		
	}

	
	@Override
	public Customer generateSactionId(Integer customerId, SanctioneLetter sanctionLetter) throws PdfNotGenerated  {
		Optional<Customer> customerdetails = cr.findById(customerId);
		Customer customerdetails1 = customerdetails.get();
		if(customerdetails.isPresent()) {
			customerdetails1.setApplicationStatus(String.valueOf(CustomerLoanStatus.SanctionLetterGenerated));

			customerdetails1.getSanctionLetter().setSanctionDate(sanctionLetter.getSanctionDate());
			customerdetails1.getSanctionLetter().setApplicationName(sanctionLetter.getApplicationName());
			customerdetails1.getSanctionLetter().setLoanAmountSanctioned(sanctionLetter.getLoanAmountSanctioned());
			customerdetails1.getSanctionLetter().setRateOfInterest(sanctionLetter.getRateOfInterest());
			customerdetails1.getSanctionLetter().setLoanTenure(sanctionLetter.getLoanTenure());
			customerdetails1.getSanctionLetter().setMonthlyEmiAmount(sanctionLetter.getMonthlyEmiAmount());
			customerdetails1.getSanctionLetter().setLoanAmountSanctioned(sanctionLetter.getLoanAmountSanctioned());
			customerdetails1.getSanctionLetter().setTermsAndCondition(sanctionLetter.getTermsAndCondition());

			logger.info("Sanction Letter PDF Generation Started");
			String title = "Paradise Finance Ltd.";
			//---------------------------------------------------------------------
//
			Document document = new Document(PageSize.A4);

			String content1 = "\n\n Dear " + customerdetails1.getCustomerLname() + customerdetails1.getCustomerFname() + customerdetails1.getCustomerMname()
					+ ","
					+ "\nParadise Finance Ltd. is Happy to informed you that your loan application has been approved. ";

			String content2 = "\n\nWe hope that you find the terms and conditions of this loan satisfactory "
					+ "and that it will help you meet your financial needs.\n\nIf you have any questions or need any assistance regarding your loan, "
					+ "please do not hesitate to contact us.\n\nWe wish you all the best and thank you for choosing us."
					+ "\n\nSincerely,\n\n" + "Ashwini kumawat(B.E) (Credit manager)";

			ByteArrayOutputStream opt = new ByteArrayOutputStream();

			PdfWriter.getInstance(document, opt);
			
			document.open();

			Image img = null;
			try {
				img = Image.getInstance("data:image/jpeg;base64,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");
				img.scalePercent(50, 50);
				img.setAlignment(Element.ALIGN_RIGHT);
				document.add(img);

			} catch (BadElementException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			Font titlefont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25);
			Paragraph titlepara = new Paragraph(title, titlefont);
			titlepara.setAlignment(Element.ALIGN_CENTER);
			document.add(titlepara);

			Font titlefont2 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
			Paragraph paracontent1 = new Paragraph(content1, titlefont2);
			document.add(paracontent1);

			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(100f);
			table.setWidths(new int[] { 2, 2 });
			table.setSpacingBefore(10);

			PdfPCell cell = new PdfPCell();
			cell.setBackgroundColor(CMYKColor.WHITE);
			cell.setPadding(5);

			Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			font.setColor(5, 5, 161);

			Font font1 = FontFactory.getFont(FontFactory.HELVETICA);
			font.setColor(5, 5, 161);

			cell.setPhrase(new Phrase("Loan amount Sanctioned", font));
			table.addCell(cell);

			cell.setPhrase(new Phrase(String.valueOf("₹ " + customerdetails1.getSanctionLetter().getLoanAmountSanctioned()),
					font1));
			table.addCell(cell);

			cell.setPhrase(new Phrase("loan tenure", font));
			table.addCell(cell);

			cell.setPhrase(new Phrase(String.valueOf(customerdetails1.getSanctionLetter().getLoanTenure()), font1));
			table.addCell(cell);

			cell.setPhrase(new Phrase("interest rate", font));
			table.addCell(cell);

			cell.setPhrase(
					new Phrase(String.valueOf(customerdetails1.getSanctionLetter().getRateOfInterest()) + " %", font1));
			table.addCell(cell);

			cell.setPhrase(new Phrase("Sanction letter generated Date", font));
			table.addCell(cell);

			Date date = new Date();
			String curdate = date.toString();
			customerdetails1.getSanctionLetter().setSanctionDate(curdate);
			cell.setPhrase(
					new Phrase(String.valueOf(customerdetails1.getSanctionLetter().getSanctionDate()), font1));
			table.addCell(cell);

			cell.setPhrase(new Phrase("Total loan Amount with Intrest", font));
			table.addCell(cell);

			document.add(table);

			Font titlefont3 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
			Paragraph paracontent2 = new Paragraph(content2, titlefont3);
			document.add(paracontent2);

			document.close();
			ByteArrayInputStream byt = new ByteArrayInputStream(opt.toByteArray());
			byte[] bytes = byt.readAllBytes();
			customerdetails1.getSanctionLetter().setSanctionLetter(bytes);
			return cr.save(customerdetails1);

			
			
			
		}
		else {
			throw new PdfNotGenerated();
		}
			
		}


	@Override
	public Optional<Customer> findById(Integer cusid) {
		
		return cr.findById(cusid);
	}


	@Override
	public Customer changeStatus(Customer customerDetails) {
		
		return cr.save(customerDetails);
	}


	@Override
	public Iterable<Customer> findByLoanStatus(String loanStatus) {
		
		return cr.findAllByApplicationStatus(loanStatus);
	}


	
}
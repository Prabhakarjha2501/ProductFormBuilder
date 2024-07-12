package com.Paradise.Homeloan.main.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDocumentsDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int documentId;
	@Lob
	private byte[] pancard;
	@Lob
	private byte[] photo;
	@Lob
	private byte[] adharcard;
	@Lob
	private byte[] salaryslip;
	@Lob
	private byte[] banksmt;
	@Lob
	private byte[] addproof;
}

package com.Paradise.Homeloan.main.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerBuilderDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int builderDetailsId;
	private String builderName;
	private String builderBankName;
	private long builderAccountNo;
	private String bankIfscNo;
	private String pancard;

}

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
public class CustomerProfessionalDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int profId;
		private String professin;
		private double customerIncome;
		private String customerDesignation;
}

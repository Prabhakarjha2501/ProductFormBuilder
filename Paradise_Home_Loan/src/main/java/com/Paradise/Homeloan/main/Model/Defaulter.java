package com.Paradise.Homeloan.main.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Defaulter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer defaulterId;
	private String customerFirstName;
	private String customerLastName;
	private String customerEmailId;
}

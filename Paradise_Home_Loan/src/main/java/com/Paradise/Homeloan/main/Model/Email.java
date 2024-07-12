package com.Paradise.Homeloan.main.Model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Email {
	String formEmail;
	String toEmail;
	String subject;
	String textMsg;
	byte[] file;
}

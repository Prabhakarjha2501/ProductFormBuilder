package com.Paradise.Homeloan.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ParadiseHomeLoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParadiseHomeLoanApplication.class, args);
	}
	@Bean
	public RestTemplate rst()
	{
		return new RestTemplate();
	}

}

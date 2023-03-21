package com.Lending.ScenarySports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//@EntityScan(basePackages = {"com.Lending.ScenarySports.Entity"})
public class ScenarySportsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScenarySportsApplication.class, args);
	}


}

package com.nttdata.bank.transaction.credit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BankTransactionCreditServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankTransactionCreditServiceApplication.class, args);
	}

}

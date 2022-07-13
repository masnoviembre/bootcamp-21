package com.nttdata.bank.credit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication (scanBasePackages = {"com.nttdata.bank.credit.controller",
                                             "com.nttdata.bank.credit.service",
                                             "com.nttdata.bank.credit.model"})
///@ComponentScan({"com.nttdata.bank.client.model"})
@EnableEurekaClient
public class BankCreditServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankCreditServiceApplication.class, args);
	}

}

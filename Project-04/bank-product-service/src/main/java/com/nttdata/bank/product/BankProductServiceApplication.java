package com.nttdata.bank.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.nttdata.bank.product.controller",
    "com.nttdata.bank.product.service",
    "com.nttdata.bank.product.model"})
@EnableEurekaClient
public class BankProductServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(BankProductServiceApplication.class, args);
  }

}

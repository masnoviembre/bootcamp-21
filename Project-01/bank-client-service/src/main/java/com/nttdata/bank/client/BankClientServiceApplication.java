package com.nttdata.bank.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication(scanBasePackages = {"com.nttdata.bank.client.controller",
                                           "com.nttdata.bank.client.service",
                                           "com.nttdata.bank.client.model"})
//@ComponentScan({"com.nttdata.bank.client.model"})
@EnableEurekaClient
public class BankClientServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(BankClientServiceApplication.class, args);
  }

}

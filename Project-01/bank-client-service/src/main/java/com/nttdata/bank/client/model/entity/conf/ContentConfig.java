package com.nttdata.bank.client.model.entity.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@Component
public class ContentConfig {
  @Bean
  public WebClient.Builder loadBalancedWebClientBuilder() {
    return WebClient.builder();
  }
}

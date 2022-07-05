package com.nttdata.bank.account.service;


import com.nttdata.bank.account.model.entity.dto.ClientDto;
import com.nttdata.bank.account.model.entity.dto.ProductDto;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ExternalService {

  @Autowired
  public WebClient.Builder webClientBuilder;

    public ClientDto externalFindByClientId(Integer clientId) {
      return webClientBuilder.baseUrl("http://localhost:8001")
        .build()
        .get()
        .uri("/clients/{clientId}", clientId)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(ClientDto.class).block();
  }

  public ProductDto externalFindByProductId(Integer productId) {
    return webClientBuilder.baseUrl("http://localhost:8002")
        .build()
        .get()
        .uri("/products/{productId}", productId)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(ProductDto.class).block();
  }


}


package com.nttdata.bank.bootcoin.sales.service;

import com.nttdata.bank.bootcoin.sales.model.document.Purse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class ExternalService {

  @Autowired
  public WebClient.Builder webClientBuilder;

  public Purse externalFindByClientId(Integer clientId) {
    return webClientBuilder.baseUrl("http://localhost:8007")
        .build()
        .get()
        .uri("/purse/byClientId/{clientId}", clientId)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(Purse.class).block();
  }
}

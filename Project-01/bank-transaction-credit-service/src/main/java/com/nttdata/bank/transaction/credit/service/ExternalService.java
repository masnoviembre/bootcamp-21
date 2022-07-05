package com.nttdata.bank.transaction.credit.service;

import com.nttdata.bank.transaction.credit.model.entity.dto.CreditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExternalService {

  @Autowired
  public WebClient.Builder webClientBuilder;

    public CreditDto externalFindCreditById(String creditNumber) {
      return webClientBuilder.baseUrl("http://localhost:8004")
        .build()
        .get()
        .uri("/credits/byCreditNumber/{creditNumber}", creditNumber)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(CreditDto.class).block();
  }

  public CreditDto externalUpdateBalance(CreditDto creditDto) {
    return webClientBuilder.baseUrl("http://localhost:8004")
        .build()
        .post()
        .uri("/credits/updCredit")
        .accept(MediaType.APPLICATION_JSON)
        .body(Mono.just(creditDto),CreditDto.class)
        .retrieve()
        .bodyToMono(CreditDto.class).block();
  }

}


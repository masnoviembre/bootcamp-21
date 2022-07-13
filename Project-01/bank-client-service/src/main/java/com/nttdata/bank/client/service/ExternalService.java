package com.nttdata.bank.client.service;


import com.nttdata.bank.client.model.entity.dto.AccountDto;
import com.nttdata.bank.client.model.entity.dto.CreditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ExternalService {

  @Autowired
  public WebClient.Builder webClientBuilder;


  public Flux<AccountDto> getAccountByClientId(Integer clientId) {
    return webClientBuilder.baseUrl("http://localhost:8003")
           .build()
           .get()
           .uri("/accounts/byClient/{clientId}", clientId)
           .accept(MediaType.APPLICATION_JSON)
           .retrieve()
           .bodyToFlux(AccountDto.class);
  }

  public Flux<CreditDto> getCreditByClientId(Integer clientId) {
    return webClientBuilder.baseUrl("http://localhost:8004")
            .build()
            .get()
            .uri("/credits/byClient/{clientId}", clientId)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToFlux(CreditDto.class);
  }

  //

  public AccountDto getAccountByAccountNumber(String accountNumber) {
    return webClientBuilder.baseUrl("http://localhost:8003")
        .build()
        .get()
        .uri("/accounts/accountNumber/{accountNumber}", accountNumber)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(AccountDto.class).block();
  }

  public CreditDto getCreditByAccountNumber(String accountNumber) {
    return webClientBuilder.baseUrl("http://localhost:8004")
        .build()
        .get()
        .uri("/credits/accountNumber/{accountNumber}", accountNumber)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(CreditDto.class).block();
  }

  public Flux<AccountDto> getAccountMovementById(Integer transactionId) {
    return webClientBuilder.baseUrl("http://localhost:8005")
        .build()
        .get()
        .uri("/transactions/accounts/{accountId}", transactionId)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToFlux(AccountDto.class);
  }

  public Flux<CreditDto> getCreditMovementById(Integer transactionId) {
    return webClientBuilder.baseUrl("http://localhost:8006")
        .build()
        .get()
        .uri("/transactions/credits/{accountId}", transactionId)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToFlux(CreditDto.class);
  }
}

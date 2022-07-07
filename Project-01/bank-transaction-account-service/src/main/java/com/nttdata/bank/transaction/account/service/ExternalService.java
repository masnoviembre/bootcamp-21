package com.nttdata.bank.transaction.account.service;

import com.nttdata.bank.transaction.account.model.entity.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExternalService {

  @Autowired
  public WebClient.Builder webClientBuilder;


  public Flux<AccountDto> externalFindAccountByCardNumber(String cardNumber) {
    return webClientBuilder.baseUrl("http://localhost:8003")
        .build()
        .get()
        .uri("/accounts/byAccountCardNumber/{NumberCard}", cardNumber)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToFlux(AccountDto.class);
  }

    public Mono<AccountDto> externalFindAccountById(String accountNumber) {
      return webClientBuilder.baseUrl("http://localhost:8003")
        .build()
        .get()
        .uri("/accounts/byAccountNumber/{accountNumber}", accountNumber)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
          .bodyToMono(AccountDto.class);
  }

  public Mono<AccountDto> externalUpdateBalance(AccountDto accountDto) {
    return webClientBuilder.baseUrl("http://localhost:8003")
        .build()
        .post()
        .uri("/accounts/updAccounts")
        .accept(MediaType.APPLICATION_JSON)
        .body(Mono.just(accountDto),AccountDto.class)
        .retrieve()
        .bodyToMono(AccountDto.class);
  }

}


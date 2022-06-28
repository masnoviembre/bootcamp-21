package com.nttdata.bank.client.service;


import com.nttdata.bank.client.model.entity.dto.AccountDto;
import com.nttdata.bank.client.model.entity.dto.CreditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExternalService {

    public  WebClient webAccount =WebClient.create("http://localhost:8003");

    private final WebClient webCredit = WebClient
                                        .builder()
                                        .baseUrl("http://localhost:8004")
                                        .build();

    public Flux<AccountDto> getAccountByClientId(Integer clientId) {
        return webAccount.get()
                .uri("/accounts/byClient/{clientId}",clientId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(AccountDto.class);
    }

    public Flux<CreditDto> getCreditByClientId(Integer clientId) {
        return webCredit.get()
                .uri("/credits/byClient/{clientId}",clientId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(CreditDto.class);
    }
}


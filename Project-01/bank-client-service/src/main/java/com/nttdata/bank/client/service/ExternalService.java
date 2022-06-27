package com.nttdata.bank.client.service;


import com.nttdata.bank.client.model.entity.dto.AccountDto;
import com.nttdata.bank.client.model.entity.dto.CreditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExternalService {

    private final WebClient webAccount = WebClient
                                         .builder()
                                         .baseUrl("http://localhost:8003")
                                         .build();

    private final WebClient webCredit = WebClient
                                        .builder()
                                        .baseUrl("http://localhost:8004")
                                        .build();
    public Mono<AccountDto> saveAccount(Integer clientId, AccountDto accountDto){
        accountDto.setClientId(clientId);
        return webAccount.post()
                         .uri("/accounts", accountDto)
                         .accept(MediaType.APPLICATION_JSON)
                         .retrieve()
                         .bodyToMono(AccountDto.class);
    }

    public Mono<CreditDto> saveCredit(Integer clientId, CreditDto creditDto){
        creditDto.setClientId(clientId);
        return webCredit.post()
                .uri("/credits", creditDto)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CreditDto.class);
    }
}


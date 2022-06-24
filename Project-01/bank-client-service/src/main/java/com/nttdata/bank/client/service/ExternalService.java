package com.nttdata.bank.client.service;


import com.nttdata.bank.client.model.document.Account;
import com.nttdata.bank.client.model.document.Credit;
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
    public Mono<Account> saveAccount(Integer clientId, Account account){
        account.setClientId(clientId);
        return webAccount.post()
                         .uri("/accounts", account)
                         .accept(MediaType.APPLICATION_JSON)
                         .retrieve()
                         .bodyToMono(Account.class);
    }

    public Mono<Credit> saveCredit(Integer clientId, Credit credit){
        credit.setClientId(clientId);
        return webCredit.post()
                .uri("/credits", credit)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Credit.class);
    }
}


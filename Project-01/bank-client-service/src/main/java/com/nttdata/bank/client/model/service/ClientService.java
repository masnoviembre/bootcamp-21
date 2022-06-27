package com.nttdata.bank.client.model.service;

import com.nttdata.bank.client.model.entity.document.Client;
import com.nttdata.bank.client.model.entity.dto.AccountDto;
import com.nttdata.bank.client.model.entity.dto.ClientDto;
import com.nttdata.bank.client.model.entity.dto.CreditDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

    Flux<Client> getAll();

    Mono<Client> save(ClientDto clientDto);

    Mono<Client> update(ClientDto clientDto);

    Mono<Void> delete(Integer clientId);

    Mono<Client> findById(Integer clientId);

    Mono<AccountDto> saveExternalAccount(Integer clientId, AccountDto accountDto);

    Mono<CreditDto> saveExternalCredit(Integer clientId, CreditDto creditDto);
}

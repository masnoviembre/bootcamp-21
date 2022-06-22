package com.nttdata.bank.client.model.service;

import com.nttdata.bank.client.model.document.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

    Flux<Client> findAll();

    Mono<Client> save(Client client);

    Mono<Client> update(Client client);

    Mono<Void> delete(Integer id);
}

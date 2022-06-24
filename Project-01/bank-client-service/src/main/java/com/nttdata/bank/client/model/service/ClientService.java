package com.nttdata.bank.client.model.service;

import com.nttdata.bank.client.model.document.Client;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

    Flux<Client> getAll();

    Mono<Client> save(Client client);

    Mono<Client> update(Client client);

    Mono<Void> delete(Integer clientId);

    Mono<Client> findById(Integer clientId);

}

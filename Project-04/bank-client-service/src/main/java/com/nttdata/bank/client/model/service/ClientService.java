package com.nttdata.bank.client.model.service;

import com.nttdata.bank.client.model.repository.entity.document.Client;
import com.nttdata.bank.client.model.repository.entity.dto.ClientDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ClientService {

  Flux<Client> getAll();

  Mono<Client> save(ClientDto clientDto);

  Mono<Client> update(ClientDto clientDto);

  Mono<Void> delete(Integer clientId);

  Mono<Client> findById(Integer clientId);

  Flux<?> getAllByClientId(Integer clientId);

  Flux<?> getAllMovements(String typeProduct, String numberProduct);

}

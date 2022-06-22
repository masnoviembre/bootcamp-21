package com.nttdata.bank.credit.model.service;

import com.nttdata.bank.credit.model.document.Credit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService {

    Flux<Credit> findAll();

    Mono<Credit> save(Credit credit);

    Flux<Credit> findByIdClient(Integer idClient);

    Mono<Credit> update(Credit credit);

    Mono<Void> delete(Integer id);
}

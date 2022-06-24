package com.nttdata.bank.credit.model.service;

import com.nttdata.bank.credit.model.document.Credit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService {

    Flux<Credit> getAll();

    Mono<Credit> save(Credit credit);

    Mono<Credit> update(Credit credit);

    Mono<Void> delete(Integer creditId);

    Mono<Credit> findById(Integer creditId);

}

package com.nttdata.bank.credit.model.service;

import com.nttdata.bank.credit.model.entity.document.Credit;
import com.nttdata.bank.credit.model.entity.dto.CreditDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService {

    Flux<Credit> getAll();

    Mono<Credit> save(Integer clientId, Integer productId, CreditDto creditDto);

    Mono<Credit> update(CreditDto creditDto);

    Mono<Void> delete(Integer creditId);

    Mono<Credit> findById(Integer creditId);

    Flux<Credit> findByClientId(Integer clientId);

}

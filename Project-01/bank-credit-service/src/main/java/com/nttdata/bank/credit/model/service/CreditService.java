package com.nttdata.bank.credit.model.service;

import com.nttdata.bank.credit.model.entity.document.Credit;
import com.nttdata.bank.credit.model.entity.dto.ClientDto;
import com.nttdata.bank.credit.model.entity.dto.CreditDto;
import com.nttdata.bank.credit.model.entity.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService {

    Flux<Credit> getAll();

    Mono<Credit> save(Integer clientId, Integer productId, CreditDto creditDto);

    Mono<Credit> update(CreditDto creditDto);

    Mono<Void> delete(Integer creditId);

    Mono<Credit> findById(Integer creditId);

    Flux<Credit> findCreditByClientId(Integer clientId);

    Mono<ClientDto> findClientByClientId(Integer clientId);

    Mono<ProductDto> findProductByProductId(Integer productId);

}

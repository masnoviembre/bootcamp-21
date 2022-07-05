package com.nttdata.bank.transaction.credit.model.service;

import com.nttdata.bank.transaction.credit.model.entity.document.TransactionCredit;
import com.nttdata.bank.transaction.credit.model.entity.dto.TransactionCreditDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionCreditService {

    Flux<TransactionCredit> getAll();

    Mono<TransactionCredit> save(TransactionCreditDto transactionCreditDto);

    Mono<TransactionCredit> update(TransactionCreditDto transactionCreditDto);

    Mono<Void> delete(Integer transactionCreditId);

    Mono<TransactionCredit> findById(Integer transactionCreditId);

    Flux<TransactionCredit> getAllByNumberCard(String numberCard);

}

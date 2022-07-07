package com.nttdata.bank.transaction.account.model.service;

import com.nttdata.bank.transaction.account.model.entity.document.TransactionAccount;
import com.nttdata.bank.transaction.account.model.entity.dto.TransactionAccountDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionAccountService {

    Flux<TransactionAccount> getAll();

    Mono<TransactionAccount> save(TransactionAccountDto transactionAccountDto);

    Mono<TransactionAccount> update(TransactionAccountDto transactionAccountDto);

    Mono<Void> delete(Integer transactionAccountId);

    Mono<TransactionAccount> findById(Integer transactionAccountId);

    Flux<TransactionAccount> getAllByCardNumber(String cardNumber);

}

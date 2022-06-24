package com.nttdata.bank.account.model.service;

import com.nttdata.bank.account.model.document.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {

    Flux<Account> getAll();

    Mono<Account> save(Account account);

    Mono<Account> update(Account account);

    Mono<Void> delete(Integer accountId);

    Mono<Account> findById(Integer accountId);

    Flux<Account> findByClientId(Integer clientId);

}

package com.nttdata.bank.account.model.service;

import com.nttdata.bank.account.model.document.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {

    Flux<Account> findAll();

    Mono<Account> save(Account account);

    Flux<Account> findByIdClient(Integer idClient);

    Mono<Account> update(Account account);

    Mono<Void> delete(Integer id);
}

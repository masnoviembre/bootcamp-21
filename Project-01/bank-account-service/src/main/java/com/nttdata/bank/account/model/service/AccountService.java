package com.nttdata.bank.account.model.service;

import com.nttdata.bank.account.model.entity.document.Account;
import com.nttdata.bank.account.model.entity.dto.AccountDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {

    Flux<Account> getAll();

    Mono<Account> save(Integer clientId, Integer productId, AccountDto accountDto);

    Mono<Account> update(AccountDto accountDto);

    Mono<Void> delete(Integer accountId);

    Mono<Account> findById(Integer accountId);

    Flux<Account> findByClientId(Integer clientId);

}

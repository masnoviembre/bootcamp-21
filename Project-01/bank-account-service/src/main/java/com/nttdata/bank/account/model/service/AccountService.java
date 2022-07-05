package com.nttdata.bank.account.model.service;

import com.nttdata.bank.account.model.entity.document.Account;
import com.nttdata.bank.account.model.entity.dto.AccountDto;
import com.nttdata.bank.account.model.entity.dto.ClientDto;
import com.nttdata.bank.account.model.entity.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.geom.Line2D;

public interface AccountService {

  Flux<Account> getAll();

  Mono<Account> save(AccountDto accountDto);

  Mono<Account> update(AccountDto accountDto);

  Mono<Void> delete(Integer accountId);

  Mono<Account> findById(Integer accountId);

  Flux<Account> findByClientId(Integer clientId);

  Mono<Account> findByAccountNumber(String accountNumber);






}

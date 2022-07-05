package com.nttdata.bank.account.service;

import com.nttdata.bank.account.model.entity.document.Account;
import com.nttdata.bank.account.model.entity.dto.*;
import com.nttdata.bank.account.model.repository.AccountRepository;
import com.nttdata.bank.account.model.service.AccountService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class AccountServiceImpl implements AccountService {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private Mapper mapper;

  @Autowired
  private ExternalService externalService;

  @Override
  public Flux<Account> getAll() {
    return accountRepository.findAll().switchIfEmpty(Flux.empty());
  }


  @Override
  public Mono<Account> save(AccountDto accountDto) {
    return accountRepository.existsById(accountDto.getAccountId())
        .flatMap(isExist -> {
            if(!isExist) {
              ClientDto p = externalService.externalFindByClientId(accountDto.getClientId());
              if (p.getClientType().equalsIgnoreCase("P")) {
                Flux<Account> accountFlux = accountRepository
                                            .findByClientId(accountDto.getClientId());
                accountFlux.hasElements()
                    .map(isAvailable -> {
                      if (!isAvailable) {
                        return accountRepository.save(mapper.map(accountDto, Account.class));
                      } else {
                        return Mono.empty();
                         }
                    });
              } else {
                      ProductDto r = externalService.externalFindByProductId(accountDto.getProductId());
                      if (r.getProductType().equalsIgnoreCase("CC")){
                          return accountRepository.save(mapper.map(accountDto, Account.class));
                      } else {
                              return Mono.empty();
                      }
              }
            }
          return Mono.empty();
        });
  }

  @Override
  public Mono<Account> update(AccountDto accountDto) {
    return accountRepository.findById(accountDto.getAccountId())
        .map(c->mapper.map(accountDto, Account.class))
        .flatMap(accountRepository::save)
        .switchIfEmpty(Mono.empty());
  }

  @Override
  public Mono<Void> delete(Integer accountId) {
    return accountRepository.findById(accountId)
        .flatMap(a->accountRepository.deleteById(a.getAccountId()))
            .switchIfEmpty(Mono.empty());
  }

  @Override
  public Mono<Account> findById(Integer accountId) {
    return this.accountRepository.findById(accountId);
  }

  @Override
  public Flux<Account> findByClientId(Integer clientId) {
    return this.accountRepository.findAll()
        .filter(p->p.getClientId()==clientId);
  }


  @Override
  public Mono<Account> findByAccountNumber(String accountNumber) {
    return this.accountRepository.findAll()
        .filter(p->p.getAccountNumber() == accountNumber ).elementAt(1);
  }


  }

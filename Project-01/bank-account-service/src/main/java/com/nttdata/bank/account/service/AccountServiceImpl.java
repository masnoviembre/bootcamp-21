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
        return accountRepository.findAll();
    }

    @Override
    public Mono<Account> save( Integer clientId, Integer productId, AccountDto accountDto) {
        Account accountMono = mapper.map(accountDto, Account.class);
        return accountRepository.save(accountMono);
    }

    @Override
    public Mono<Account> update(AccountDto accountDto) {
        Account accountMono = mapper.map(accountDto, Account.class);
        return accountRepository.save(accountMono);
    }

    @Override
    public Mono<Void> delete(Integer accountId) {
        return this.accountRepository.deleteById(accountId);
    }

    @Override
    public Mono<Account> findById(Integer accountId) {
        return this.accountRepository.findById(accountId);
    }

    @Override
    public Flux<Account> findAccountByClientId(Integer clientId) {
        return this.accountRepository.findAll()
                .filter(p->p.getClientId()==clientId);
    }

    @Override
    public Mono<ClientDto> findClientByClientId(Integer clientId){
        return externalService.findClientByClientId(clientId);
    }

    @Override
    public Mono<ProductDto> findProductByProductId(Integer productId){
        return externalService.findProductByProductId(productId);
    }

}

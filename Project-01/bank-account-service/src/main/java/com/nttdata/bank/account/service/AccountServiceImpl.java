package com.nttdata.bank.account.service;

import com.nttdata.bank.account.model.document.Account;
import com.nttdata.bank.account.model.repository.AccountRepository;
import com.nttdata.bank.account.model.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Flux<Account> getAll() {
        return this.accountRepository.findAll();
    }

    @Override
    public Mono<Account> save(Account account) {
        return this.accountRepository.save(account);
    }

    @Override
    public Mono<Account> update(Account account) {
        return this.accountRepository.save(account);
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
    public Flux<Account> findByClientId(Integer clientId) {
        return this.accountRepository.findAll()
                .filter(p->p.getClientId()==clientId);
    }
}

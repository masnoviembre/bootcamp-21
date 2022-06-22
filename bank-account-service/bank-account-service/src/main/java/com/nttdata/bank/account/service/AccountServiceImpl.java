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
    public Flux<Account> findAll() {
        return this.accountRepository.findAll();
    }

    @Override
    public Mono<Account> save(Account account) {
        return this.accountRepository.save(account);
    }

    @Override
    public Flux<Account> findByIdClient(Integer idClient) {
        return this.accountRepository.findAll()
                                     .filter(p->p.getIdClient()==idClient);
    }

    @Override
    public Mono<Account> update(Account account) {
        return this.accountRepository.save(account);
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return this.accountRepository.deleteById(id);
    }
}

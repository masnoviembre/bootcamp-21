package com.nttdata.bank.account.controller;

import com.nttdata.bank.account.model.document.Account;
import com.nttdata.bank.account.model.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public Flux<Account> getAll(){
        return accountService.getAll();
    }

    @GetMapping("/{accountId}")
    public Mono<Account> getById(@PathVariable("accountId") Integer accountId){
        return accountService.findById(accountId);
    }

    @GetMapping("/byClient/{clientId}")
    public Flux<Account> getByClientId(@PathVariable("clientId") int clientId){
        return accountService.findByClientId(clientId);
    }

    @PostMapping
    public Mono<Account> save(@RequestBody Account account){
        return accountService.save(account);
    }

    @PostMapping("/updAccounts")
    public Mono<Account> update(@RequestBody Account account){
        return accountService.update(account);
    }

    @PostMapping("/delete/{accountId}")
    public Mono<Void> deleteBy(@PathVariable("accountId") Integer accountId){
        return accountService.delete(accountId);
    }

}

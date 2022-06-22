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

    @GetMapping("/getAccounts")
    public Flux<Account> getAccount(){
        return accountService.findAll();
    }

    @GetMapping("/getAccountsByIdClient/{idClient}")
    public Flux<Account> getAccountByIdClient(@PathVariable("idClient") int idClient){
        return accountService.findByIdClient(idClient);
    }

    @PostMapping("/postAccounts")
    public Mono<Account> saveAccount(@RequestBody Account account){
        return accountService.save(account);
    }

    @PostMapping("/updAccounts")
    public Mono<Account> updateAccount(@RequestBody Account account){
        return accountService.update(account);
    }

    @PostMapping("/delete/{id}")
    public Mono<Void> deleteAccount(@PathVariable("id") Integer id){
        return accountService.delete(id);
    }

}

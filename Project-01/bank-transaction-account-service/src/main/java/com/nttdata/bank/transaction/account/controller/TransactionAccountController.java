package com.nttdata.bank.transaction.account.controller;

import com.nttdata.bank.transaction.account.model.entity.document.TransactionAccount;
import com.nttdata.bank.transaction.account.model.entity.dto.TransactionAccountDto;
import com.nttdata.bank.transaction.account.model.service.TransactionAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transactions/accounts")
public class TransactionAccountController {

    @Autowired
    private TransactionAccountService transactionAccountService;

    @GetMapping
    public Flux<TransactionAccount> getAll(){
        return transactionAccountService.getAll();
    }

    @GetMapping("/{accountId}")
    public Mono<TransactionAccount> getById(@PathVariable("transactionAccountId") Integer transactionAccountId){
        return transactionAccountService.findById(transactionAccountId);
    }

    @PostMapping
    public Mono<TransactionAccount> save(@PathVariable("accountId") Integer accountId,
                                         @RequestBody TransactionAccountDto transactionAccountDto){
        return transactionAccountService.save(accountId, transactionAccountDto);
    }

    @PostMapping("/updTransaction")
    public Mono<TransactionAccount> update(@RequestBody TransactionAccountDto transactionAccountDto){
        return transactionAccountService.update(transactionAccountDto);
    }

    @PostMapping("/delete/{transactionAccountId}")
    public Mono<Void> deleteBy(@PathVariable("transactionAccountId") Integer transactionAccountId){
        return transactionAccountService.delete(transactionAccountId);
    }

}

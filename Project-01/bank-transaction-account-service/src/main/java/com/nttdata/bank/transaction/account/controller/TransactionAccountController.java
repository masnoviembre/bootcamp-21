package com.nttdata.bank.transaction.account.controller;

import com.nttdata.bank.transaction.account.model.entity.document.TransactionAccount;
import com.nttdata.bank.transaction.account.model.entity.dto.TransactionAccountDto;
import com.nttdata.bank.transaction.account.model.service.TransactionAccountService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
    public Flux<TransactionAccount> getAllByNumberCard(@PathVariable("transactionAccountId") String accountNumber){
        return transactionAccountService.getAllByNumberCard(accountNumber);
    }

    @GetMapping("/getAllByNumberCard/{numberCard}")
    public Mono<TransactionAccount> getAllByNumberCard (@PathVariable("numberCard") Integer transactionAccountId){
        return transactionAccountService.findById(transactionAccountId);
    }

    @CircuitBreaker(name = "saveCB", fallbackMethod = "fallBackSave")
    @PostMapping
    public Mono<TransactionAccount> save(@RequestBody TransactionAccountDto transactionAccountDto){
        return transactionAccountService.save(transactionAccountDto);
    }

    @PostMapping("/updTransaction")
    public Mono<TransactionAccount> update(@RequestBody TransactionAccountDto transactionAccountDto){
        return transactionAccountService.update(transactionAccountDto);
    }

    @CircuitBreaker(name = "deleteCB", fallbackMethod = "fallBackDeleteById")
    @PostMapping("/delete/{transactionAccountId}")
    public Mono<Void> deleteById(@PathVariable("transactionAccountId") Integer transactionAccountId){
        return transactionAccountService.delete(transactionAccountId);
    }

    public Flux<?> fallBackSave (@RequestBody TransactionAccountDto transactionAccountDto,
                                             RuntimeException e ){
        return Flux.just("Petición en espera");
    }

    public Flux<?> fallBackDeleteById (@RequestBody TransactionAccountDto transactionAccountDto,
                                 RuntimeException e ){
        return Flux.just("Petición en espera");
    }

}

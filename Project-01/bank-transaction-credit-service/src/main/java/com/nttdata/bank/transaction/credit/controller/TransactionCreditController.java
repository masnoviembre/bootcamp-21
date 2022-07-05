package com.nttdata.bank.transaction.credit.controller;

import com.nttdata.bank.transaction.credit.model.entity.document.TransactionCredit;
import com.nttdata.bank.transaction.credit.model.entity.dto.TransactionCreditDto;
import com.nttdata.bank.transaction.credit.model.service.TransactionCreditService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transactions/credits")
public class TransactionCreditController {

    @Autowired
    private TransactionCreditService transactionCreditService;

    @GetMapping
    public Flux<TransactionCredit> getAll(){
        return transactionCreditService.getAll();
    }

    @GetMapping("/{creditId}")
    public Mono<TransactionCredit> getById(@PathVariable("transactionCreditId") Integer transactionCreditId){
        return transactionCreditService.findById(transactionCreditId);
    }

    @CircuitBreaker(name = "saveCB", fallbackMethod = "fallBackSave")
    @PostMapping
    public Mono<TransactionCredit> save(@RequestBody TransactionCreditDto transactionCreditDto){
        return transactionCreditService.save(transactionCreditDto);
    }

    @PostMapping("/updCredit")
    public Mono<TransactionCredit> update(@RequestBody TransactionCreditDto transactionAccountDto){
        return transactionCreditService.update(transactionAccountDto);
    }

    @CircuitBreaker(name = "deleteCB", fallbackMethod = "fallBackDeleteById")
    @PostMapping("/delete/{creditId}")
    public Mono<Void> deleteById(@PathVariable("transactionCreditId") Integer transactionCreditId){
        return transactionCreditService.delete(transactionCreditId);
    }

    public Flux<?> fallBackSave (@RequestBody TransactionCreditDto transactionCreditDto,
                                 RuntimeException e ){
        return Flux.just("Petición de transacciones de Credito en espera");
    }

    public Flux<?> fallBackDeleteById (@PathVariable("transactionCreditId") Integer transactionCreditId,
                                 RuntimeException e ){
        return Flux.just("Petición de transacciones de Credito en espera");
    }

}

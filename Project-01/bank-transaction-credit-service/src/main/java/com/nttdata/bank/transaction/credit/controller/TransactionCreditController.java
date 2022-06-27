package com.nttdata.bank.transaction.credit.controller;


import com.nttdata.bank.transaction.credit.model.entity.document.TransactionCredit;
import com.nttdata.bank.transaction.credit.model.entity.dto.TransactionCreditDto;
import com.nttdata.bank.transaction.credit.model.service.TransactionCreditService;
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

    @PostMapping
    public Mono<TransactionCredit> save(@PathVariable("creditId") Integer creditId,
                                         @RequestBody TransactionCreditDto transactionCreditDto){
        return transactionCreditService.save(creditId, transactionCreditDto);
    }

    @PostMapping("/updCredit")
    public Mono<TransactionCredit> update(@RequestBody TransactionCreditDto transactionAccountDto){
        return transactionCreditService.update(transactionAccountDto);
    }

    @PostMapping("/delete/{creditId}")
    public Mono<Void> deleteBy(@PathVariable("transactionCreditId") Integer transactionCreditId){
        return transactionCreditService.delete(transactionCreditId);
    }

}

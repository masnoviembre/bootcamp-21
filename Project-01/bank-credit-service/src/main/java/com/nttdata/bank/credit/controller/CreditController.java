package com.nttdata.bank.credit.controller;

import com.nttdata.bank.credit.model.document.Credit;
import com.nttdata.bank.credit.model.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/credits")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @GetMapping
    public Flux<Credit> getAll(){
        return creditService.getAll();
    }

    @GetMapping("/{creditId}")
    public Mono<Credit> getById(@PathVariable("creditId") Integer creditId){
        return creditService.findById(creditId);
    }

    @PostMapping
    public Mono<Credit> save(@RequestBody Credit credit){
        return creditService.save(credit);
    }

    @PostMapping("/updCredit")
    public Mono<Credit> update(@RequestBody Credit credit){
        return creditService.update(credit);
    }

    @PostMapping("/delete/{creditId}")
    public Mono<Void> deleteBy(@PathVariable("creditId") Integer creditId){
        return creditService.delete(creditId);
    }

}

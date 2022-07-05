package com.nttdata.bank.credit.controller;

import com.nttdata.bank.credit.model.entity.document.Credit;
import com.nttdata.bank.credit.model.entity.dto.CreditDto;
import com.nttdata.bank.credit.model.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

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

    @GetMapping("/byClient/{clientId}")
    public Flux<Credit> getCreditByClientId(@PathVariable("clientId") Integer clientId){
        return creditService.findByClientId(clientId);
    }

    @PostMapping
    public Mono<Credit> save(@Valid @RequestBody CreditDto creditDto){
        return creditService.save(creditDto);
    }

    @PostMapping("/updCredit")
    public Mono<Credit> update(@RequestBody CreditDto creditDto){
        return creditService.update(creditDto);
    }

    @PostMapping("/delete/{creditId}")
    public Mono<Void> deleteBy(@PathVariable("creditId") Integer creditId){
        return creditService.delete(creditId);
    }

}

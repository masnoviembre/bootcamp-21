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

    @GetMapping("/getCredits")
    public Flux<Credit> getCredit(){
        return creditService.findAll();
    }

    @GetMapping("/getCreditsByIdClient/{idClient}")
    public Flux<Credit> getCreditByIdClient(@PathVariable("idClient") int idClient){
        return creditService.findByIdClient(idClient);
    }

    @PostMapping("/postCredits")
    public Mono<Credit> saveCredit(@RequestBody Credit credit){
        return creditService.save(credit);
    }

    @PostMapping("/updCredits")
    public Mono<Credit> updateCredit(@RequestBody Credit credit){
        return creditService.update(credit);
    }

    @PostMapping("/delete/{id}")
    public Mono<Void> deleteCredit(@PathVariable("id") Integer id){
        return creditService.delete(id);
    }


}

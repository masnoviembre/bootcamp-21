package com.nttdata.bank.client.controller;

import com.nttdata.bank.client.model.document.Account;
import com.nttdata.bank.client.model.document.Client;
import com.nttdata.bank.client.model.document.Credit;
import com.nttdata.bank.client.model.service.ClientService;
import com.nttdata.bank.client.service.ExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ExternalService externalService;

    @GetMapping
    public Flux<Client> getAll(){
        return clientService.getAll();
    }

    @GetMapping("/{clientId}")
    public Mono<Client> getById(@PathVariable("clientId") Integer clientId){
        return clientService.findById(clientId);
    }

    @PostMapping
    public Mono<Client> save(@RequestBody Client client){
        return clientService.save(client);
    }

    @PostMapping("/updClients")
    public Mono<Client> update(@RequestBody Client client){
        return clientService.update(client);
    }

    @PostMapping("/delete/{clientId}")
    public Mono<Void> deleteByClientId(@PathVariable("clientId") Integer clientId){
        return clientService.delete(clientId);
    }

    @PostMapping("/accounts/{clientId}")
    public Mono<Account> saveAccount(@PathVariable("clientId") Integer clientId, @RequestBody Account account){
        return externalService.saveAccount(clientId, account);
    }

    @PostMapping("/credits/{clientId}")
    public Mono<Credit> saveCredit(@PathVariable("clientId") Integer clientId, @RequestBody Credit credit){
        return externalService.saveCredit(clientId, credit);
    }
}

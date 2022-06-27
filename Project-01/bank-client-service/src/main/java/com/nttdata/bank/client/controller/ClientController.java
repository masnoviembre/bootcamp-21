package com.nttdata.bank.client.controller;

import com.nttdata.bank.client.model.entity.document.Client;
import com.nttdata.bank.client.model.entity.dto.AccountDto;
import com.nttdata.bank.client.model.entity.dto.ClientDto;
import com.nttdata.bank.client.model.entity.dto.CreditDto;
import com.nttdata.bank.client.model.service.ClientService;
import com.nttdata.bank.client.service.ExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public Flux<Client> getAll(){
        return clientService.getAll();
    }

    @GetMapping("/{clientId}")
    public Mono<Client> getById(@PathVariable("clientId") Integer clientId){
        return clientService.findById(clientId);
    }

    @PostMapping
    public Mono<Client> save(@Valid @RequestBody ClientDto clientDto){ return clientService.save(clientDto);
    }

    @PostMapping("/updClients")
    public Mono<Client> update(@Valid @RequestBody ClientDto clientDto){
        return clientService.update(clientDto);
    }

    @PostMapping("/delete/{clientId}")
    public Mono<Void> deleteByClientId(@PathVariable("clientId") Integer clientId){
        return clientService.delete(clientId);
    }

    @PostMapping("/accounts/{clientId}")
    public Mono<AccountDto> saveAccount(@PathVariable("clientId") Integer clientId, @RequestBody AccountDto accountDto){
        return clientService.saveExternalAccount(clientId, accountDto);
    }

    @PostMapping("/credits/{clientId}")
    public Mono<CreditDto> saveCredit(@PathVariable("clientId") Integer clientId, @RequestBody CreditDto creditDto){
        return clientService.saveExternalCredit(clientId, creditDto);
    }
}

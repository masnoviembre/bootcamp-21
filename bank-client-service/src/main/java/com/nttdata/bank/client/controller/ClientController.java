package com.nttdata.bank.client.controller;


import com.nttdata.bank.client.model.document.Client;
import com.nttdata.bank.client.model.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/getClients")
    public Flux<Client> getClient(){
        return clientService.findAll();
    }

    @PostMapping("/postClients")
    public Mono<Client> saveClient(@RequestBody Client client){
        return clientService.save(client);
    }

    @PostMapping("/updClients")
    public Mono<Client> updateClient(@RequestBody Client client){
        return clientService.update(client);
    }

    @PostMapping("/delete/{id}")
    public Mono<Void> deleteClient(@PathVariable("id") Integer id){
        return clientService.delete(id);
    }

}

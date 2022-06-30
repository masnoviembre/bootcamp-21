package com.nttdata.bank.client.controller;

import com.nttdata.bank.client.model.entity.document.Client;
import com.nttdata.bank.client.model.entity.dto.AccountDto;
import com.nttdata.bank.client.model.entity.dto.ClientDto;
import com.nttdata.bank.client.model.entity.dto.CreditDto;
import com.nttdata.bank.client.model.service.ClientService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clients")
public class ClientController {

  @Autowired
  private ClientService clientService;

  @GetMapping
  public Flux<Client> getAll() {
    return clientService.getAll();
  }

  @GetMapping("/{clientId}")
  public Mono<Client> getById(@PathVariable("clientId") Integer clientId) {
    return clientService.findById(clientId);
  }

  @GetMapping("/accounts/{clientId}")
  public Flux<AccountDto> getAccountByClientId(@PathVariable("clientId") Integer clientId) {
    return clientService.getAccountByClientId(clientId);
  }

  @GetMapping("/credits/{clientId}")
  public Flux<CreditDto> getCreditByClientId(@PathVariable("clientId") Integer clientId) {
    return clientService.getCreditByClientId(clientId);
  }

  @PostMapping
  public Mono<Client> save(@Valid @RequestBody ClientDto clientDto) {
    return clientService.save(clientDto);
  }

  @PostMapping("/updClients/{clientId}")
  public Mono<Client> update(@Valid @RequestBody ClientDto clientDto) {
    return clientService.update(clientDto);
  }

  @PostMapping("/delete/{clientId}")
  public Mono<Void> deleteByClientId(@PathVariable("clientId") Integer clientId) {
    return clientService.delete(clientId);
  }

}

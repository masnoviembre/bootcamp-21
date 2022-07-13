package com.nttdata.bank.client.controller;

import com.nttdata.bank.client.model.entity.document.Client;
import com.nttdata.bank.client.model.entity.dto.AccountDto;
import com.nttdata.bank.client.model.entity.dto.ClientDto;
import com.nttdata.bank.client.model.entity.dto.CreditDto;
import com.nttdata.bank.client.model.service.ClientService;
import com.nttdata.bank.client.model.service.KafkaCreditListener;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/clients")
public class ClientController {

  @Autowired
  private ClientService clientService;

  @Autowired
  KafkaCreditListener listener;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Flux<Client> getAll() {
    return clientService.getAll();
  }

  @GetMapping("/{clientId}")
  public Mono<Client> getById(@PathVariable("clientId") Integer clientId) {
    return clientService.findById(clientId);
  }

  @PostMapping
  public Mono<Client> save(@Valid @RequestBody ClientDto clientDto) {
    return clientService.save(clientDto);
  }

  @PostMapping("/updClients")
  public Mono<Client> update(@Valid @RequestBody ClientDto clientDto) {
    return clientService.update(clientDto);
  }

  @PostMapping("/delete/{clientId}")
  public Mono<Void> deleteByClientId(@PathVariable("clientId") Integer clientId) {
    return clientService.delete(clientId);
  }


  //@CircuitBreaker(name = "allCB", fallbackMethod = "fallBackGetAllByClientId")
  @GetMapping("/allProducts/{clientId}/")
  public Flux<AccountDto> getAllByClientId(@PathVariable("clientId") Integer clientId) {
    return clientService.getAllByClientId(clientId);
  }

  public Flux<?> fallBackGetAllByClientId(@PathVariable("clientId") Integer clientId,
                                                      RuntimeException e) {
    return Flux.just("Petición desde clientes en espera");
  }


  //
  @GetMapping(value = "credits", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<CreditDto> credits() {
    return listener.getCredits();
  }

}

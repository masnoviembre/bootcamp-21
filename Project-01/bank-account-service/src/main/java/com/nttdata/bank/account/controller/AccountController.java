package com.nttdata.bank.account.controller;

import com.nttdata.bank.account.model.entity.document.Account;
import com.nttdata.bank.account.model.entity.dto.AccountDto;
import com.nttdata.bank.account.model.entity.dto.ClientDto;
import com.nttdata.bank.account.model.service.AccountService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  @Autowired
  private AccountService accountService;

  @GetMapping
  public Flux<Account> getAll() {
    return accountService.getAll();
  }

  @GetMapping("/{accountId}")
  public Mono<Account> getById(@PathVariable("accountId") Integer accountId) {
    return accountService.findById(accountId);
  }

  @GetMapping("/byClient/{clientId}")
  public Flux<Account> getAccountByClientId(@PathVariable("clientId") Integer clientId) {
    return accountService.findByClientId(clientId);
  }

  @GetMapping("/byAccountNumber/{accountNumber}")
  public Mono<Account> getAccountByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
    return accountService.findByAccountNumber(accountNumber);
  }

  @GetMapping("/balanceByClientId/{clientId}")
  public Flux<Object> getBalanceByClientId(@PathVariable("clientId") Integer clientId) {
    return accountService.getBalanceByClientId(clientId);
  }

  @PostMapping
  public Mono<Account> save(@RequestBody AccountDto accountDto) {
    return accountService.save(accountDto);
  }

  @PostMapping("/updAccounts")
  public Mono<Account> update(@RequestBody AccountDto accountDto) {
    return accountService.update(accountDto);
  }

  @PostMapping("/delete/{accountId}")
  public Mono<Void> deleteBy(@PathVariable("accountId") Integer accountId) {
    return accountService.delete(accountId);
  }

  @PostMapping("/updBalance/{accountId}/{amount}")
  public Mono<Account> UpdBalance(@PathVariable("accountId") Integer accountId,
                                  @PathVariable("amount") Float amount) {
    return accountService.UpdBalance(accountId, amount);
  }


}

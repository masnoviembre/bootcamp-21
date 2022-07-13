package com.nttdata.bank.bootcoin.purchase.controller;

import com.nttdata.bank.bootcoin.purchase.model.document.Purchase;
import com.nttdata.bank.bootcoin.purchase.model.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

  @Autowired
  private PurchaseService purchaseService;

  @PostMapping
  public Mono<Purchase> save (@RequestBody Purchase purchase) {
    return purchaseService.save(purchase);
  }

  @GetMapping
  public Flux<Purchase> getAll() { return purchaseService.getAll(); }

}

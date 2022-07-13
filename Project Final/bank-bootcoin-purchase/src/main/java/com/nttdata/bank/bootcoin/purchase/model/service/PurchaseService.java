package com.nttdata.bank.bootcoin.purchase.model.service;

import com.nttdata.bank.bootcoin.purchase.model.document.Purchase;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PurchaseService {

    Mono<Purchase> save(Purchase purchase);

    Flux<Purchase> getAll();

    void sendMessage(String topic, Purchase purchase);
}

package com.nttdata.bank.bootcoin.sales.model.service;

import com.nttdata.bank.bootcoin.sales.model.document.Purchase;
import com.nttdata.bank.bootcoin.sales.model.document.Sales;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface SalesService {

  Mono<Sales> save (Sales sales);

  Flux<Sales> getAll();

  List<Purchase> getPurchase();



}

package com.nttdata.bank.bootcoin.purchase.service;

import com.nttdata.bank.bootcoin.purchase.model.document.Purchase;
import com.nttdata.bank.bootcoin.purchase.model.repository.PurchaseRepository;
import com.nttdata.bank.bootcoin.purchase.model.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PurchaseServiceImpl implements PurchaseService {

  @Autowired
  private PurchaseRepository purchaseRepository;

  @Override
  public Mono<Purchase> save(Purchase purchase) {
    return purchaseRepository.existsById(purchase.getPurchaseId())
        .flatMap((isExist -> {
        if ( !isExist) {
          return purchaseRepository.save(purchase);
        } else {
          return Mono.empty();
        }
        }));
  }

  @Override
  public Flux<Purchase> getAll() {
    return purchaseRepository.findAll().switchIfEmpty(Flux.empty());
  }
}

package com.nttdata.bank.bootcoin.sales.service;

import com.nttdata.bank.bootcoin.sales.model.document.Purchase;
import com.nttdata.bank.bootcoin.sales.model.document.Purse;
import com.nttdata.bank.bootcoin.sales.model.document.Sales;
import com.nttdata.bank.bootcoin.sales.model.repository.SalesRepository;
import com.nttdata.bank.bootcoin.sales.model.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {

  List<Purchase> purchases = new ArrayList<>();

  @Autowired
  private SalesRepository salesRepository;

  @Autowired
  private ExternalService externalService;

  @Override
  public Flux<Sales> getAll() {
    return salesRepository.findAll().switchIfEmpty(Flux.empty());
  }


  @Override
  public Mono<Sales> save(Sales sales) {
      return salesRepository.existsById(sales.getSalesId())
        .flatMap((isExist -> {
          if (!isExist) {
            return salesRepository.save(sales);
          } else {
            return Mono.empty();
          }
        }));
  }

  @KafkaListener(topics="purchaseTopic", containerFactory = "kafkaListenerContainerFactory", groupId = "myGroup")
  public void listenTopic(Purchase purchase) {
    System.out.println("Ha llegado: "+purchase.getPurchaseId() );
    purchases.add(purchase);

    Sales sales = new Sales();
    sales.setSalesId(purchase.getPurchaseId());
    sales.setPurchaseClientId(purchase.getPurchaseClientId());
    sales.setAmount(purchase.getAmount());
    sales.setPayMode(purchase.getPayMode());

    salesRepository.save(sales);
  }

  @Override
  public List<Purchase> getPurchase() {
    return purchases;
  }
}

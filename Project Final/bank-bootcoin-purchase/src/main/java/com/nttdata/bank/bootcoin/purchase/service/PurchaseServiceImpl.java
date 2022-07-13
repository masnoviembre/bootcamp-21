package com.nttdata.bank.bootcoin.purchase.service;

import com.nttdata.bank.bootcoin.purchase.model.document.Purchase;
import com.nttdata.bank.bootcoin.purchase.model.repository.PurchaseRepository;
import com.nttdata.bank.bootcoin.purchase.model.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PurchaseServiceImpl implements PurchaseService {

  @Value("${topic}")
  String topic;

  @Autowired
  private PurchaseRepository purchaseRepository;

  @Autowired
  KafkaTemplate kafkaTemplate;

  @Override
  public Flux<Purchase> getAll() {
    return purchaseRepository.findAll().switchIfEmpty(Flux.empty());
  }

  @Override
  public Mono<Purchase> save(Purchase purchase) {
    return purchaseRepository.existsById(purchase.getPurchaseId())
        .flatMap((isExist -> {
          if (!isExist) {
            sendMessage(topic, purchase);
            return purchaseRepository.save(purchase);
          } else {
            return Mono.empty();
          }
        }));
  }

  @Override
  public void sendMessage(String topic, Purchase purchase) {
    ListenableFuture<SendResult<String, Purchase>> future = kafkaTemplate.send(topic, purchase);
    future.addCallback(new ListenableFutureCallback<SendResult<String, Purchase>>() {
      @Override
      public void onSuccess(SendResult<String, Purchase> result) {
        System.out.println("Enviado " + result.getProducerRecord().value().getPurchaseId() +
            " al topic " + result.getRecordMetadata().topic());
      }

      @Override
      public void onFailure(Throwable ex) {
        ex.printStackTrace();
      }
    });
  }
}
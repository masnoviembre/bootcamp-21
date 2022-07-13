package com.nttdata.bank.bootcoin.purchase.model.repository;

import com.nttdata.bank.bootcoin.purchase.model.document.Purchase;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends ReactiveMongoRepository<Purchase, Integer> {
}

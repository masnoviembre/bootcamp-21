package com.nttdata.bank.bootcoin.sales.model.repository;

import com.nttdata.bank.bootcoin.sales.model.document.Sales;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends ReactiveMongoRepository<Sales, Integer> {
}

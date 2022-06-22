package com.nttdata.bank.client.model.repository;

import com.nttdata.bank.client.model.document.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product,Integer> {
}

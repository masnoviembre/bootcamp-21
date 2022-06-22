package com.nttdata.bank.client.model.service;

import com.nttdata.bank.client.model.document.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Flux<Product> findAll();

    Mono<Product> save(Product product);

    Mono<Product> update(Product product);

    Mono<Void> delete(Integer id);
}

package com.nttdata.bank.product.model.service;

import com.nttdata.bank.product.model.document.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Flux<Product> getAll();

    Mono<Product> save(Product product);

    Mono<Product> update(Product product);

    Mono<Void> delete(Integer productId);

    Mono<Product> findById(Integer productId);

}

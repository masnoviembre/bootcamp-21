package com.nttdata.bank.product.model.service;

import com.nttdata.bank.product.model.entity.document.Product;
import com.nttdata.bank.product.model.entity.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

  Flux<Product> getAll();

  Mono<Product> save(ProductDto productDto);

  Mono<Product> update(ProductDto productDto);

  Mono<Void> delete(Integer productId);

  Mono<Product> findById(Integer productId);

}

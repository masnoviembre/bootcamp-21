package com.nttdata.bank.product.service;

import com.nttdata.bank.product.model.entity.document.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@ConditionalOnProperty(name = "cache.enabled", havingValue = "true")
public class ProductServiceImplCache extends ProductServiceImpl {

  private static final String KEY = "product";

  @Autowired
  private ReactiveHashOperations<String, Integer, Product> hashOperations;

  @Override
  public Mono<Product> findById(Integer id) {
    System.out.println("Pasa por cache");
    return hashOperations.get(KEY, id)
        .switchIfEmpty(this.getFromDatabaseAndCache(id));
  }

  private Mono<Product> getFromDatabaseAndCache(Integer id) {
    return super.findById(id)
                .flatMap(dto -> this.hashOperations.put(KEY, id, dto)
                    .thenReturn(dto));
  }

}

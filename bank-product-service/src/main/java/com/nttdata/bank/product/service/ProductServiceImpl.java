package com.nttdata.bank.product.service;

import com.nttdata.bank.product.model.entity.document.Product;
import com.nttdata.bank.product.model.entity.dto.ProductDto;
import com.nttdata.bank.product.model.repository.ProductRepository;
import com.nttdata.bank.product.model.service.ProductService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
//@ConditionalOnProperty(name = "cache.enabled", havingValue = "false")
public class ProductServiceImpl implements ProductService {

  private static final String KEY = "product";

  @Autowired
  private ReactiveHashOperations<String, Integer, Product> hashOperations;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private Mapper mapper;

  @Override
  public Flux<Product> getAll() {
    return this.productRepository.findAll().switchIfEmpty(Flux.empty());
  }

  @Override
  public Mono<Product> save(ProductDto productDto) {
    return productRepository.existsById(productDto.getProductId())
        .flatMap((isExist -> {
          if (!isExist) {
            return productRepository.save(mapper.map(productDto, Product.class));
          } else {
            return Mono.empty();
          }
        }));
  }

  @Override
  public Mono<Product> update(ProductDto productDto) {
    return productRepository.findById(productDto.getProductId())
        .flatMap(p -> productRepository.save(mapper.map(productDto, Product.class)))
        .switchIfEmpty(Mono.empty());
  }

  @Override
  public Mono<Void> delete(Integer productId) {
    return productRepository.findById(productId)
        .flatMap(p -> productRepository.deleteById(p.getProductId()))
        .switchIfEmpty(Mono.empty());
  }

  @Override
  public Mono<Product> findById(Integer productId) {
    //return this.productRepository.findById(productId);
    return hashOperations.get(KEY, productId)
        .switchIfEmpty(this.getFromDatabaseAndCache(productId));
  }

  private Mono<Product> getFromDatabaseAndCache(Integer id) {
    System.out.println("Busca en base datos " + id);
    return productRepository.findById(id)
        .flatMap(dto -> hashOperations.put(KEY, id, dto)
            .thenReturn(dto));
  }


}

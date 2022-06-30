package com.nttdata.bank.product.service;

import com.nttdata.bank.product.model.entity.document.Product;
import com.nttdata.bank.product.model.entity.dto.ProductDto;
import com.nttdata.bank.product.model.repository.ProductRepository;
import com.nttdata.bank.product.model.service.ProductService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

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
                    if (!isExist)
                        return productRepository.save(mapper.map(productDto, Product.class));
                    else
                        return Mono.empty();
                }));
    }

    @Override
    public Mono<Product> update(ProductDto productDto) {
        return productRepository.findById(productDto.getProductId())
                .flatMap(p->productRepository.save(mapper.map(productDto, Product.class)))
                .switchIfEmpty(Mono.empty());
     }

    @Override
    public Mono<Void> delete(Integer productId) {
        return productRepository.findById(productId)
                .flatMap(p->productRepository.deleteById(p.getProductId()))
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Product> findById(Integer productId) {
        return this.productRepository.findById(productId);
    }
}

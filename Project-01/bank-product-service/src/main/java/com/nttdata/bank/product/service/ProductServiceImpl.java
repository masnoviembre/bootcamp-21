package com.nttdata.bank.product.service;

import com.nttdata.bank.product.model.document.Product;
import com.nttdata.bank.product.model.repository.ProductRepository;
import com.nttdata.bank.product.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Flux<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Mono<Product> save(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Mono<Product> update(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Mono<Void> delete(Integer productId) {
        return this.productRepository.deleteById(productId);
    }

    @Override
    public Mono<Product> findById(Integer productId) {
        return this.productRepository.findById(productId);
    }
}

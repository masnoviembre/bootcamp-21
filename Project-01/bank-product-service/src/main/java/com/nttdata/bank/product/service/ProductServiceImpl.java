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

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public Flux<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Mono<Product> save(ProductDto productDto) {
        Product productMono = mapper.map(productDto, Product.class);
        return productRepository.save(productMono);
    }

    @Override
    public Mono<Product> update(ProductDto productDto) {
        Product productMono = mapper.map(productDto, Product.class);
        return this.productRepository.save(productMono);
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

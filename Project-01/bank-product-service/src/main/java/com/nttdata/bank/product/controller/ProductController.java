package com.nttdata.bank.product.controller;

import com.nttdata.bank.product.model.document.Product;
import com.nttdata.bank.product.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Flux<Product> getAll(){
        return productService.getAll();
    }

    @GetMapping("/{productId}")
    public Mono<Product> getById(@PathVariable("productId") Integer productId){
        return productService.findById(productId);
    }

    @PostMapping
    public Mono<Product> save(@RequestBody Product product){
        return productService.save(product);
    }

    @PostMapping("/updProducts")
    public Mono<Product> update(@RequestBody Product product){
        return productService.update(product);
    }

    @PostMapping("/delete/{productId}")
    public Mono<Void> deleteBy(@PathVariable("productId") Integer productId){
        return productService.delete(productId);
    }

}

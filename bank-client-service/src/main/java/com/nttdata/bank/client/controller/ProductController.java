package com.nttdata.bank.client.controller;

import com.nttdata.bank.client.model.document.Product;
import com.nttdata.bank.client.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getProducts")
    public Flux<Product> getProduct(){
        return productService.findAll();
    }

    @PostMapping("/postProducts")
    public Mono<Product> saveProduct(@RequestBody Product product){
        return productService.save(product);
    }

    @PostMapping("/updProducts")
    public Mono<Product> updateProduct(@RequestBody Product product){
        return productService.update(product);
    }

    @PostMapping("/delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable("id") Integer id){
        return productService.delete(id);
    }

}

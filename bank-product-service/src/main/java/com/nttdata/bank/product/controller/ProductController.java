package com.nttdata.bank.product.controller;

import com.nttdata.bank.product.model.entity.document.Product;
import com.nttdata.bank.product.model.entity.dto.ProductDto;
import com.nttdata.bank.product.model.service.ProductService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping
  public Flux<Product> getAll() {
    return productService.getAll();
  }

  @GetMapping("/{productId}")
  public Mono<Product> getById(@PathVariable("productId") Integer productId) {
    return productService.findById(productId);
  }

  @PostMapping
  public Mono<Product> save(@Valid @RequestBody ProductDto productDto) {
    return productService.save(productDto);
  }

  @PostMapping("/updProducts")
  public Mono<Product> update(@Valid @RequestBody ProductDto productDto) {
    return productService.update(productDto);
  }

  @PostMapping("/delete/{productId}")
  public Mono<Void> deleteBy(@PathVariable("productId") Integer productId) {
    return productService.delete(productId);
  }

}

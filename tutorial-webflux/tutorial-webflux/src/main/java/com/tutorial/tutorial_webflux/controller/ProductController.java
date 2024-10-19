/*package com.tutorial.tutorial_webflux.controller;

import com.tutorial.tutorial_webflux.entity.Product;
import com.tutorial.tutorial_webflux.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

//ESTUDIAR LOMBOK, ENTITY,  AUTOWIRED

@RestController
@RequestMapping("/product")
@Slf4j
//@RequiredArgsConstructor
public class ProductController {


    @Autowired
    private ProductService productService;

    //public ProductController(ProductService productService) {
       // this.productService = productService;
   // }


    @GetMapping("/{id}")
    public Mono<Product> getById(@PathVariable("id") int id){

        return productService.getById(id);
    }

    @PostMapping
    public Mono<Product> save(@RequestBody Product product){

        return productService.save(product);
    }

    @PutMapping("/{id}")
    public Mono<Product> update(@PathVariable("id") int id, @RequestBody Product product){

        return productService.update(id, product);
    }

    @DeleteMapping
    public Mono<Void> deleteById(@PathVariable("id") int id){

        return productService.delete(id);
    }
}*/

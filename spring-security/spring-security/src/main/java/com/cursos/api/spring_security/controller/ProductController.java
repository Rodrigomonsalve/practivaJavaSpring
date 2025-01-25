package com.cursos.api.spring_security.controller;

import com.cursos.api.spring_security.dto.SaveCategory;
import com.cursos.api.spring_security.dto.SaveProduct;
import com.cursos.api.spring_security.persistence.entity.Product;

import com.cursos.api.spring_security.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    //http://localhost:9191/api/v1/products?page=1&size=5   //ESTUDIAR PAGEABLES Y NOMBRE DE APIS EN application.properties (http://localhost:9191/api/v1/products?p=0&limit=5)
    @GetMapping
    public ResponseEntity<Page<Product>> findAll(Pageable pageable){
        Page<Product> productsPage=productService.findAll(pageable);

        if(productsPage.hasContent()){

            return ResponseEntity.ok(productsPage);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> findOneById(@PathVariable Long productId){
        Optional<Product> product=productService.findById(productId);

        if(product.isPresent()){
            return ResponseEntity.ok(product.get());
        }

        return ResponseEntity.notFound().build();
    }


    @PostMapping()
    public ResponseEntity<Product> createOne(@RequestBody @Valid SaveProduct saveProduct){

        Product product = productService.createOne(saveProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateOneById(@PathVariable Long productId, @RequestBody @Valid SaveProduct saveProduct){

        Product product = productService.updateOneById(productId,saveProduct);
        return ResponseEntity.ok(product);
    }


    @PutMapping("/{productId}/disable")//ESTUDIAR ARQUITECTURA DE APIS (controladores, stores y colecciones)
    public ResponseEntity<Product> disableOneById(@PathVariable Long productId){

        Product product = productService.disableOneById(productId);
        return ResponseEntity.ok(product);
    }
}

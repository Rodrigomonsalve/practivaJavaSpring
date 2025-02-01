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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

//En este controlador estamos usando la autorizacion "basada en  metodos" mediante las anotaciones @PreAuthorize. Esto sustituye a la autorizacion basada en coincidencias de url establecida en la clase HttpSecurityConfig.
//Es importante mencionar que la autorizacion "basada en  metodos" mediante las anotaciones como @PreAuthorize, se pueden establecer en el controlador(como es el caso), en el servicio, en el serviceImpl, o en el repositorio. Si lo haces en el repositorio, sería necesario sobrescribir los metodos del JpaRepository para poder colocar la anotacion @PreAuthorize.
//En caso de que el usuario no este autorizado, a diferencia de la autorizacion "basada en coincidencias", devolverá un error 500Internal Server Error, lo cual no es correcto. Por eso se creó el manejador de excepciones que se encuatra en GlobalExceptionHandler, para que devuelva un error 403Forbiden.
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    //http://localhost:9191/api/v1/products?page=1&size=5   //ESTUDIAR PAGEABLES Y NOMBRE DE APIS EN application.properties (http://localhost:9191/api/v1/products?p=0&limit=5)
    //@PreAuthorize("hasAnyRole('ADMINISTRATOR', 'ASSISTANT_ADMINISTRATOR')")
    @PreAuthorize("hasAuthority('READ_ALL_PRODUCTS')")
    @GetMapping
    public ResponseEntity<Page<Product>> findAll(Pageable pageable){
        Page<Product> productsPage=productService.findAll(pageable);

        if(productsPage.hasContent()){

            return ResponseEntity.ok(productsPage);
        }

        return ResponseEntity.notFound().build();
    }

    //@PreAuthorize("hasAnyRole('ADMINISTRATOR', 'ASSISTANT_ADMINISTRATOR')")
    @PreAuthorize("hasAuthority('READ_ONE_PRODUCT')")
    @GetMapping("/{productId}")
    public ResponseEntity<Product> findOneById(@PathVariable Long productId){
        Optional<Product> product=productService.findById(productId);

        if(product.isPresent()){
            return ResponseEntity.ok(product.get());
        }

        return ResponseEntity.notFound().build();
    }

    //@PreAuthorize("hasRole('ADMINISTRATOR')")
    @PreAuthorize("hasAuthority('CREATE_ONE_PRODUCT')")
    @PostMapping()
    public ResponseEntity<Product> createOne(@RequestBody @Valid SaveProduct saveProduct){

        Product product = productService.createOne(saveProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    //@PreAuthorize("hasAnyRole('ADMINISTRATOR', 'ASSISTANT_ADMINISTRATOR')")
    @PreAuthorize("hasAuthority('UPDATE_ALL_PRODUCTS')")
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateOneById(@PathVariable Long productId, @RequestBody @Valid SaveProduct saveProduct){

        Product product = productService.updateOneById(productId,saveProduct);
        return ResponseEntity.ok(product);
    }

    //@PreAuthorize("hasRole('ADMINISTRATOR')")
    @PreAuthorize("hasAuthority('DISABLE_ONE_PRODUCT')")
    @PutMapping("/{productId}/disable")//ESTUDIAR ARQUITECTURA DE APIS (controladores, stores y colecciones)
    public ResponseEntity<Product> disableOneById(@PathVariable Long productId){

        Product product = productService.disableOneById(productId);
        return ResponseEntity.ok(product);
    }
}

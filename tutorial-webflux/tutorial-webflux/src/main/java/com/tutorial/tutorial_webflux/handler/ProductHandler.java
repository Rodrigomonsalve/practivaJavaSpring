package com.tutorial.tutorial_webflux.handler;

import com.tutorial.tutorial_webflux.entity.Product;
import com.tutorial.tutorial_webflux.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ProductHandler {

// ESTA ES UNA CLASE HANDLER. SE ENCARGA DE PERSONALIZAR Y MANEJAR LAS RESPUESTAS HTTP.
// ES NECESARIO CREARLA PORQUE EN SPRING MVC SE ENCARGABA DE ESTO EL CONTROLADOR, PERO EN NUESTRO CASO ESTAMOS SUPONIENDO QUE YA NO LO TENEMOS.
// NO ES INDISPENSABLE CREAR UNA CLASE HANDLER.

    private final ProductService productService;

    public ProductHandler(ProductService productService) {
        this.productService = productService;
    }

    public Mono<ServerResponse> getAll(ServerRequest request){ // SERVERRESPONSE SE USA EN WEBFLUX PARA CREAR RESPUESTAS HTTP DE FORMA REACTIVA. EN SPRING MVC UTILIZÁAMOS RESPONSEENTITY.

        Flux<Product> products = productService.getAll();

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(products, Product.class); // .OK DA UNA RESPUESTA 200 OK. APPLICATION JSON SIGNIFICA QUE LA RESPUESA DEBE SER EN FORMATO JSON. PRODUCTS ES EL OBJETO QUE SE RESPONDERÁ EN FORMATO JSON. SIN EMBARGO, ES IMPORTANTE SABER QUE EL FORMATO JSON ES EL UTILIZADO POR DEFECTO POR SPRING.
    }

    public Mono<ServerResponse> getOne(ServerRequest request){

        int id = Integer.valueOf(request.pathVariable("id"));

        Mono<Product> product = productService.getById(id);

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(product, Product.class);
    }

    public Mono<ServerResponse> save(ServerRequest request){

        Mono<Product> product = request.bodyToMono(Product.class);

        return product.flatMap(p -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(productService.save(p), Product.class));
    }

    public Mono<ServerResponse> update(ServerRequest request){

        int id = Integer.valueOf(request.pathVariable("id"));

        Mono<Product> product = request.bodyToMono(Product.class);

        return product.flatMap(p -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(productService.update(id, p), Product.class));
    }

    public Mono<ServerResponse> delete(ServerRequest request){

        int id = Integer.valueOf(request.pathVariable("id"));

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(productService.delete(id), Product.class);
    }
}

package com.tutorial.tutorial_webflux.service;

import com.tutorial.tutorial_webflux.entity.Product;
import com.tutorial.tutorial_webflux.exception.CustomException;
import com.tutorial.tutorial_webflux.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// EN LA PROGRAMACION REACTIVA LOS DATOS SE PROCESAN A MEDIDA QUE ESTAN DISPONIBLES. ESTO SIGNIFICA QUE LA EJECUCIÒN DEL CODIGO NO SE BLOQUEA. NO HAY QUE ESPERAR HASTA QUE LOS DATOS TERMINEN DE LLEGAR(BUSQUEDA EN BASE DE DATOS)
// LO ANTERIOR SE LOGRA CON LAS CLASES MONO Y FLUX.
// MONO --> CUANDO ESPERAS RECIBIR UN SOLO DATO O NINGUNO
// FLUX --> CUANDO ESPERAS RECIBIR DE 1 A N DATOS O UN ERROR. "Flux<Product> product" -> product es como una lista, pero en la que no tienes los elementos en un solo momento. Los vas obteniendo conforme los encuentra, mientras el codigo continua ejecutandose.
// POR LO ANTERIOR SIEMPRE SUSTITUYE UNA LISTA O CUALQUIER TIPO DE OBJETO POR Mono<XX> o Flux<xx> AL QUERER OBTENER DE O ENVIAR ALGO A LA BD.
// ESTA ES LA ESENCIA DE WEBFLUX

//EN UN ENTORNO REACTIVO EL MANEJO DE EXCPECIONES SE DEBE HACER DE IGUAL FORMA REACTIVA(Mono.error(new Exception.....)).

@Service
@Slf4j
//@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
       this.productRepository = productRepository;
   }

    public Flux<Product> getAll(){

        return productRepository.findAll();
    }

    /*public Mono<Product> getById(int id){

        return productRepository.findById(id);
    }*/

    public Mono<Product> getById(int id){

        return productRepository.findById(id)

        //.switchIfEmpty(Mono.error(new Exception("product not Found"))); // switchIfEmpty sirve para crear una consecuencia en caso de que findById no traiga nada. Solo sirve en  webflux
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "product not Found")));
    }

    /*public Mono<Product> save(Product product)

        return productRepository.save(product);
    }*/

    public Mono<Product> save(Product product){

        Mono<Boolean> existsName = productRepository.findByName(product.getName()).hasElement();

        // flatMap lo que hace es aplanar Monos anidados en caso de que lleguen así (hay que recordar que los resultados son asicronos).
        // Se usa flatMap cuando vamos a trasformar un mono o flux en otro mono o flux. En este caso, necesitamos obtener un Mono<Product>, pero utilizamos como entrada un Mono<Boolean>.
        // flatMap siempre debe recibir una funcion (lambda) como parametro.
        // En este caso, "exists" es la variable en la que se guarda el valor de "existsName"

        //return existsName.flatMap(exists -> exists ? Mono.error(new Exception("Product already in use")) : productRepository.save(product));
        return existsName.flatMap(exists -> exists ? Mono.error(new CustomException(HttpStatus.NOT_FOUND, "product not Found")) : productRepository.save(product));
    }

    /*public Mono<Product> update(int id, Product product){

        return productRepository.save(new Product(id, product.getName(), product.getPrice()));

    }*/

    public Mono<Product> update(int id, Product product){

        Mono<Boolean> productId = productRepository.findById(id).hasElement();// El metodo hasElement solo se usa con webflux. Devuelve un true o un false; true si el metodo,en este caso findById sí trae algo de la base de datos.
        Mono<Boolean> productRepeatedName = productRepository.repeatedName(id, product.getName()).hasElement();

        return productId.flatMap(
                existsId -> existsId ?
                        productRepeatedName.flatMap(existsName -> existsName ? Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "product not Found"))
                                : productRepository.save(new Product(id, product.getName(), product.getPrice())))
                        : Mono.error(new CustomException(HttpStatus.NOT_FOUND, "product not Found")));
    }

    public Mono<Void> delete(int id){

        Mono<Boolean> productId = productRepository.findById(id).hasElement();

        return productId.flatMap(existsId -> existsId ? productRepository.deleteById(id) : Mono.error(new CustomException(HttpStatus.NOT_FOUND, "product not Found")));
    }




}

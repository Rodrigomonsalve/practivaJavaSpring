package com.tutorial.tutorial_webflux.repository;

import com.tutorial.tutorial_webflux.entity.Product;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product,Integer> {

    Mono<Product> findByName(String name);

    // Vemos  que puedes crear metodos abstractos, pero con un query. Es tanto como si estuviera desarrollada.
    // id <> id sigifica "id" diferente a "id". Si se ingresa un 7, entnces seria id diferente a 7.
    // Los dos puntos (:) sigifican parametros. En una consulta en sql directamente, no los usarias:
    // SELECT * FROM product WHERE id <> 7 AND name = Rodrigo;
    @Query("SELECT * FROM product WHERE id <> :id AND name = :name")
    Mono<Product> repeatedName(int id, String name);
}

package com.tutorial.tutorial_webflux.router;


import com.tutorial.tutorial_webflux.handler.ProductHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

//EN WEBFLUX SE PUEDE USAR UNA CLASE CONTROLADORA O UN ROUTER COMO ESTE. CUALQUIERA DE LAS 2 FUNCIONA CORRECTAMENTE, Y SEGUIRÍA SIENDO CÓDIGO NO BLOQUEANTE.


@Configuration
@Slf4j
public class ProductRouter {

    private static final String PATH="product";

    @Bean
    public WebProperties.Resources resources(){

        return new WebProperties.Resources();
    }

    @Bean
    RouterFunction<ServerResponse> router(ProductHandler handler){



        // Cuando desde el frontend se envía la entidad, en nuestro caso "Product" (save, update), se envìa de forma automática mediante el proceso de deserializacion, y al usar el operador "de referencia a metodos".
        // Lo anterior no ocurre con MVC. Ahi tenemos que usar la anotacion @RequestBody en el controlador.

        //::  Este es el operador "de referencia a metodos". Sirve para referenciar metodos, sin tener que ejecutarlos directamente, ni de forma inmediata. Tampoco es necesario pasarle argumentos.
        return RouterFunctions.route()
                .GET(PATH, handler::getAll)
                .GET(PATH + "/{id}", handler::getOne)
                .POST(PATH, handler::save)
                .PUT(PATH + "/{id}", handler::update)
                .DELETE(PATH + "/{id}", handler::delete)
                .build();
    }
}

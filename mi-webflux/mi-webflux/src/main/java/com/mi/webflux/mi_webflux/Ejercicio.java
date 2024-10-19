package com.mi.webflux.mi_webflux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/")
public class Ejercicio {

    @GetMapping("/hola")
    public Mono<String> hello(){

        //Mono<String> mono=Mono.just("Hola Mundo");
        //mono.subscribe(value -> System.out.println(value));

        return Mono.just("Hola Mundo");
    }

    @GetMapping("/numbers")
    public Flux<Integer> numbers(){

        return Flux.range(1, 10).map(number -> number * 2);
    }

    @GetMapping("/serviceA")
    public Mono<String> serviceA(){

        return Mono.just("Respuesta de A");
    }

    @GetMapping("/serviceB")
    public Mono<String> serviceB(){

        return Mono.just("Respuesta de B");
    }

    @GetMapping("/combine")
    public Mono<String> combine(){

        Mono<String> serviceAResponse = serviceA();
        Mono<String> serviceBResponse = serviceB();

        return Mono.zip(serviceAResponse, serviceBResponse, (a,b) -> a + " y " + b);
    }

   @GetMapping(value = "/stream", produces = "text/event-stream")
    public Flux<Long> stream(){

        return Flux.interval(Duration.ofSeconds(1)).map( i -> (long) (Math.random() * 100));


    }






}

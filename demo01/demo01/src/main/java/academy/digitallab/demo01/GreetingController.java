package academy.digitallab.demo01;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value = "greeting")
public class GreetingController {

    private final AtomicLong counter = new AtomicLong();
    private static final String template = "Hello %s"; // A %s se le denomina marcador de posicion, y sirve para imprimir el valor que el cliente en su navegador

    @GetMapping
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name){

        return new Greeting(counter.incrementAndGet(), String.format(template,name));// Template es "Hello %s", y name es el marcador de posición (%s). Indica lo que se va a sustituir.
                                                                                        // El .format se utilizar  para poder usar marcadores de posiciones
    }
}

package com.tutorial.tutorial_webflux.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Optional;

// LA FINALIDAD DE ESTA CLASE ES CONTROLAR, DE FORMA CENTRALIZADA, LAS DEMAS EXCEPCIONES QUE SE PRESENTEN Y QUE NO TENGAN UN TRY-CATCH, ES DECIR, TODOS LOS ERRORES QUE SE VEN EN CASCADA EN LA CONSOLA.
// EN ESTE CODIGO NO LO HAY, PERO ESTA CLASE SE INVOCA DE FORMA AUTOMATICA DESPUÉS DE LA EJECUCIÓN DE LAS CLASES EN LOS CONTROLADORES QUE TENGAN LA ANOTACIÓN @ExceptionHandler (se ve en el tema de API de Pildoras Informaticas).
@Component
@Slf4j
public class GlobalExceptionHandler extends AbstractErrorWebExceptionHandler {

    public GlobalExceptionHandler(ErrorAttributes errorAttributes, WebProperties.Resources resources, ApplicationContext applicationContext, ServerCodecConfigurer codecConfigurer) {
        super(errorAttributes, resources, applicationContext);

        this.setMessageReaders(codecConfigurer.getReaders());
        this.setMessageWriters(codecConfigurer.getWriters());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::customErrorResponse);
    }

    private Mono<ServerResponse> customErrorResponse(ServerRequest request) {


        Map<String, Object> errorMap=this.getErrorAttributes(request, ErrorAttributeOptions.defaults());
        HttpStatus status = (HttpStatus) Optional.ofNullable(errorMap.get("status")).orElse(HttpStatus.INTERNAL_SERVER_ERROR);

        return ServerResponse.status(status).contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(errorMap));


    }


}

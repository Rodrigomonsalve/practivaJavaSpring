package com.tutorial.tutorial_webflux.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
//import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.HashMap;
import java.util.Map;

//LA ANOTACION @ExceptionHandler SE OCUPA PARA PERSOALIZAR LA RESPUESTA EN CASO DE QUE LLEGUE A PRESETARSE UNA EXCEPCION, DESDE LOS CONTROLADORES.
// LAS CLASES QUE HEREDAN DE DefaultErrorAttributes HACEN LO MISMO, PERO DE FORMA GLOBAL.

@Component
public class CustomAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {

        Map<String, Object> errorAttributes=new HashMap<>();// ESTO HACE LAS VECES DE LA ENTIDAD QUE CONTIENE LOS ATRIBUTOS DE LA RESPUESTA.
        Throwable throwable=super.getError(request);

        if(throwable instanceof CustomException){
            CustomException customException = (CustomException) throwable;

            //LO QUE SIGUE ES EL CONTENIDO DEL JSON QUE SE ARROJARÁ EN CASO DE ERROR.
            errorAttributes.put("status", customException.getStatus());
            errorAttributes.put("message", customException.getMessage());
        }

        return errorAttributes;
    }
}

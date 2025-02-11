package com.cursos.api.spring_security.handlerExc;

import com.cursos.api.spring_security.dto.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.Column;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

//LAS EXEPCIONES DENTRO DEL PAQUETE "handelExcpetion", SE DESARRAOLLAN POR SEPARADO A LAS DEMAS, PORQUE LAS EXCEPCIONES GENERADAS POR ERRORES EN LA AUTORIZACION  BASADAS EN COINCIDENCIAS DE URLS, SE GENERAN EN UN MOMENTO DIFERENTE A LAS EXCEPCIONES GEERADAS POR ERRORES EN LA AUTORIZACION  BASADAS EN METODOS.
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {


//Este metodo es para manejar errores de AUTENTICACION(errores de jwt). Hay que recordar que es necesario porque la autorizacion basada en coincicencias de urls no diferencia entre errores de autenticacion y autorizacion.
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        ApiError apiError=new ApiError();
        apiError.setBackendMessage(authException.getLocalizedMessage());
        apiError.setUrl(request.getRequestURI().toString());
        apiError.setMethod(request.getMethod());
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setMessage("No se encontraron credenciales. Inicia sesion");

        //AuthenticationEntryPoint devuelve errores para jsp, thymeleaf, por lo que es necesario convertir la respuesta a json.
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String apiErrorAsJson=objectMapper.writeValueAsString(apiError);

        response.getWriter().write(apiErrorAsJson);

    }
}

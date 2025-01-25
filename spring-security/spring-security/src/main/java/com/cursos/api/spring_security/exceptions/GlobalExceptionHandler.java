package com.cursos.api.spring_security.exceptions;

import com.cursos.api.spring_security.dto.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //EXCEPCION QUE SE LANZA COMO JSON CUANDO HAY UN ERROR GENERICO(EJEMPLO: SE INTENTA GUARDAR UN PRODUCTO EN UNA CATEGORIA INEXISTENTE)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?>  handlerGenericException(HttpServletRequest request, Exception exception){
        ApiError apiError=new ApiError();
        apiError.setBackendMessage(exception.getLocalizedMessage());
        apiError.setUrl(request.getRequestURI().toString());
        apiError.setMethod(request.getMethod());
        apiError.setMessage("Error interno en el servidor");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    //EXCEPCION QUE SE LANZA COMO JSON SÓLO CUANDO FALLAN LAS VALIDACIONES
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?>  handlerMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException exception){
        ApiError apiError=new ApiError();
        apiError.setBackendMessage(exception.getLocalizedMessage());
        apiError.setUrl(request.getRequestURI().toString());
        apiError.setMethod(request.getMethod());()
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setMessage("Error en la peticion enviada");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

}

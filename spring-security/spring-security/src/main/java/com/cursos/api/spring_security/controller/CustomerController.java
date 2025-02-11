package com.cursos.api.spring_security.controller;

import com.cursos.api.spring_security.dto.RegisteredUser;
import com.cursos.api.spring_security.dto.SaveUser;
import com.cursos.api.spring_security.persistence.entity.security.User;
import com.cursos.api.spring_security.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

//En este controlador estamos usando la autorizacion "basada en  metodos" mediante las anotaciones @PreAuthorize. Esto sustituye a la autorizacion basada en coincidencias de url establecida en la clase HttpSecurityConfig.
//Es importante mencionar que la autorizacion "basada en  metodos" mediante las anotaciones como @PreAuthorize, se pueden establecer en el controlador(como es el caso), en el servicio, en el serviceImpl, o en el repositorio. Si lo haces en el repositorio, sería necesario sobrescribir los metodos del JpaRepository para poder colocar la anotacion @PreAuthorize.
//En caso de que el usuario no este autorizado, a diferencia de la autorizacion "basada en coincidencias", devolverá un error 500Internal Server Error, lo cual no es correcto. Por eso se creó el manejador de excepciones que se encuentra en GlobalExceptionHandler, para que devuelva un error 403Forbiden. Sin embargo, siempre devolverá un AccessDeniedException sin importar si el usuario está autenticado o no, o solamente no está autorizado, lo que no sucede con la autorizacion basada en coincidencias de url.
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private AuthenticationService authenticationService;

    //Con este controlador creamos nuevos usuarios. (se registran)
    //http://localhost:9191/api/v1/customers
    @PreAuthorize("permitAll")  //URL PUBLICA
    @PostMapping
    public ResponseEntity<RegisteredUser> registerOne(@RequestBody @Valid SaveUser newUser){ //Ingresa en el body de la peticion, un json con la información necesaria para registrar al nuevo usuario.
         /* {
                "username": "Pepito02",
                "name": "Pepito Lopez",
                "password":"67463HEy",
                "repeatedPassword":"67463HEy"
            }*/

        RegisteredUser registeredUser=authenticationService.registerOneCustomer(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);

    }

    @PreAuthorize("denyAll")  //URL PROHIBIDA PARA TODOS. ES SOLO UN EJEMPLO.
    @GetMapping
    public ResponseEntity<List<User>> findAll(){

       return ResponseEntity.ok(Arrays.asList());
    }
}

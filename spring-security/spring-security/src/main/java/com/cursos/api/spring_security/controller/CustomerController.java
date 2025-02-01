package com.cursos.api.spring_security.controller;

import com.cursos.api.spring_security.dto.RegisteredUser;
import com.cursos.api.spring_security.dto.SaveUser;
import com.cursos.api.spring_security.persistence.entity.User;
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

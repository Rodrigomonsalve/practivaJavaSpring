package com.cursos.api.spring_security.controller;

import com.cursos.api.spring_security.dto.AuthenticationResponse;
import com.cursos.api.spring_security.persistence.entity.User;
import com.cursos.api.spring_security.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


//En este controlador estamos usando la autorizacion "basada en  metodos" mediante las anotaciones @PreAuthorize. Esto sustituye a la autorizacion basada en coincidencias de url establecida en la clase HttpSecurityConfig.
//Es importante mencionar que la autorizacion "basada en  metodos" mediante las anotaciones como @PreAuthorize, se pueden establecer en el controlador(como es el caso), en el servicio, en el serviceImpl, o en el repositorio. Si lo haces en el repositorio, sería necesario sobrescribir los metodos del JpaRepository para poder colocar la anotacion @PreAuthorize.
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    //Este controlador tiene como finalidad autenticar(validar) a usuarios ya registrados ateriormente.
    //http://localhost:9191/api/v1/auth/authenticate
    @PreAuthorize("permitAll")  //URL PUBLICA
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest){ //El authenticationRequest es un dto que lleva los datos de un usuario ya registrado. Es como si un usuario intentara loguearse:

            /*{
                "username": "lmarquez",
                "password":"clave123"
             }*/

        AuthenticationResponse rsp=authenticationService.login(authenticationRequest); //Aqui se valida usuario y contraseña y, en caso de ser correctos, devuelve un jwt.
        return ResponseEntity.ok(rsp);  //Se devuelve el dto con el jwt generado.
    }


    //Este metodo sólo sirve para validar si cualquier jwt generado es válido.
    //El jwt se pone manualmente en la url.
    //http://localhost:9191/api/v1/auth/validate-token?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9DVVNUT01FUiIsIm5hbWUiOiJsdWlzIG3DoXJxdWV6IiwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6IlJFQURfTVlfUFJPRklMRSJ9XSwic3ViIjoibG1hcnF1ZXoiLCJpYXQiOjE3MzgxNzk2OTUsImV4cCI6MTczODE4MTQ5NX0.B2KapDFPMZe2Rq3WPdlM3WzSBw_-uUcnnStQsiOodHk
    @PreAuthorize("permitAll")  //URL PUBLICA
    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validate(@RequestParam String jwt){

        boolean  isTokenValid=authenticationService.validateToken(jwt);
        return ResponseEntity.ok(isTokenValid);
    }


    //El siguiente metodo sirve para poder visualizar en un json, los datos del usuario ya registrado. Se verá algo como:
    /*{
        "id": 1,
            "username": "lmarquez",
            "name": "luis márquez",
            "password": "$2a$10$IjorW0xEHmFM07qD7.bwXui4hePvBzJ2tz59ev8K.fFUO5CesYF1K",
            "role": "ROLE_CUSTOMER",
            "enabled": true,
            "accountNonExpired": true,
            "credentialsNonExpired": true,
            "authorities": [
        {
            "authority": "READ_MY_PROFILE"
        }
    ],
        "accountNonLocked": true
    }*/
    //http://localhost:9191/api/v1/auth/profile
    //Se debe enviar dentro de las  cabeceras http el jwt vigente del usuario respectivo. Es todos lo que se envia. Nada mas. /EN POSTMAN: Authorization -> Auth Type (Bearer token) -> Colocas el token generado cuando el usuario se autentica (http://localhost:9191/api/v1/auth/authenticate). Si el token es incorrecto, no esta vigente o se no se manda token dara un error 403Forbiden.
    //Si nos fijamos, el jwt no pasa en ningun momento como parametro. Quien se encarga de interceptar las cabeceras, siempre, despues de toda peticion, es el filtro.

    //@PreAuthorize("hasAnyRole('ADMINISTRATOR', 'ASSISTANT_ADMINISTRATOR', 'CUSTOMER')")
    @PreAuthorize("hasAuthority('READ_MY_PROFILE')")
    @GetMapping("/profile")
    public ResponseEntity<User> findMyProfile(){
        User user=authenticationService.findLoggedInUser();
        return ResponseEntity.ok(user);                     //Retorna el json referido.

    }
}

package com.cursos.api.spring_security.service.auth;

import com.cursos.api.spring_security.controller.AuthenticationRequest;
import com.cursos.api.spring_security.dto.AuthenticationResponse;
import com.cursos.api.spring_security.dto.RegisteredUser;
import com.cursos.api.spring_security.dto.SaveUser;
import com.cursos.api.spring_security.exceptions.ObjectNotFoundException;
import com.cursos.api.spring_security.persistence.entity.User;
import com.cursos.api.spring_security.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    //Este es el metodo para registrar a un nuevo usuario. Su finalidad es devolver en formato json, los datos del nuevo usuario registrado, incluido su jwt. No es un servicio destinado a interactuar con base de datos. Se invoca desde el controlador "CustomerController".
    public RegisteredUser registerOneCustomer(@Valid SaveUser newUser) {

        User user=userService.registerOneCustomer(newUser); //El nuevo usuario es guardado en la base de datos.

        RegisteredUser userDTO=new RegisteredUser(); //RegisteredUser es el dto de User que tiene los datos que nos interesa devolver del usuario registrado.
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole().name());

        String jwt = jwtService.generateToken(user, generateExtraClaims(user)); //Se crea el jwt. Los extraClaims es sólo un map con ciertos datos del usuario registrado, que jwt pide para generarse. Formará parte del payload. Podemos pasar los datos que nosotros queramos.
        userDTO.setJwt(jwt);

        return userDTO;
    }
    //ESTO ES UN EJEMPLO DE LO QUE DEVUELVE EL METODO ANTERIOR:
    /*{
        "id": 1,
            "name": "Pepito Lopez",
            "username": "Pepito02",
            "role": "ROLE_CUSTOMER",
            "jwt": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.
            eyJyb2xlIjoiUk9MRV9DVVNUT01FUiIsIm5hbWUiOiJQZXBpdG8gTG9wZXoiLCJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiUkVBRF9NWV9QUk9GSUxFIn1dLCJzdWIiOiJQZXBpdG8wMiIsImlhdCI6MTczODEwNzQ0MCwiZXhwIjoxNzM4MTA5MjQwfQ.
            wQgHIAeorDeEdjWAxBe4L8qibEhtyAAEbvSOENwIT4s"
    }*/

    //Para generar los extraClaims que formará parte del payload del jwt.
    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims=new HashMap<>();
        extraClaims.put("name",user.getName());
        extraClaims.put("role",user.getRole().name());
        extraClaims.put("authorities",user.getAuthorities());

        return extraClaims;
    }


    //Metodo que sirve para autenticar(validar) a un usuario ya registrado anteriormente. Digamos que es el paso 2. Retorna un dto que sólo se conforma del campo jwt.
    public AuthenticationResponse login(AuthenticationRequest authRequest) {   //El autheRequest es un dto que lleva los datos de un usuario ya registrado. Viene desde AuthenticationController:

            /*{
                "username": "lmarquez",
                "password":"clave123"
             }*/

        Authentication authentication=new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword());
        authenticationManager.authenticate(authentication); //Compara las credenciales del usuario(username y password). En caso de ser correctas cotinua con el flujo del código. Si falla lanza una excepcion. En nuestro caso usa a DaoAuthenticationProvider para la validacion.

        UserDetails user=userService.findOneByUsername(authRequest.getUsername()).get();
        String jwt=jwtService.generateToken(user,generateExtraClaims((User)user));  //Se genera otro token(jwt) por el usuario registrado. La duracion del token esta definida en el application.properties.

        AuthenticationResponse authRsp=new AuthenticationResponse();
        authRsp.setJwt(jwt);

        return authRsp;

    }


    public boolean validateToken(String jwt) {

        try {
            jwtService.extractUsername(jwt);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    //Metodo que sirve que retorna los datos del usuario.

    public User findLoggedInUser() {

        //Cuando el usuario validó su jwt de forma correcta(SE AUTENTICO) en el filtro, esa autennticacion se guarda en SecurityContextHolder.
        Authentication auth=(UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Este es el objeto authentication(AuthenticationService): "+auth);//UsernamePasswordAuthenticationToken [Principal=lmarquez, Credentials=[PROTECTED], Authenticated=true, Details=WebAuthenticationDetails [RemoteIpAddress=0:0:0:0:0:0:0:1, SessionId=null], Granted Authorities=[READ_MY_PROFILE]]

            String username=(String) auth.getPrincipal();  //Con getPrincipal se obtiene el atributo username.

            return userService.findOneByUsername(username)
                    .orElseThrow(()->new ObjectNotFoundException("User not found"));

        }
}

package com.cursos.api.authorization_server.controller;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// EN EL AUTHORIZATION-SERVER SÓLO SERÁN NECESARIAS 2 RUTAS(login y logout).
// NO SON APIS.
// HAY QUE RECORDAR QUE EN EL APPLICATION.PROPERTIES ESTÁ LA SIGUIENTE CLAVE: server.servlet.context-path=/authorization-server
@Controller
public class LoginController {

// /authorization-server/login
//Aqui se redirige una vez que el cliente(aplicación) se autentica exitosamente. En ambiente de pruebas, esta proceso se lleva a cabo con oauthdebugger.com
    @GetMapping("/login")
    public String login(){

        return "login";
    }

// /authorization-server/logout
    @GetMapping("/logout")
    public String logout(){

        return "logout";

    }

    @PostMapping("/logout")
    public String logoutOk(HttpSecurity http) throws Exception {

        http.logout(logoutConfig->{
            logoutConfig.logoutSuccessUrl("/login?logout")
                    .deleteCookies("JSESSIONID")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true);
        });

        return "login?logout";
    }
}

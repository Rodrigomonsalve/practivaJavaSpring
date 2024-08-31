package com.cyberslex.Security.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


// Al heredarse la clase AbstractSecurityWebApplicationInitializer, automáticamènte se regitsra un filtro de seguridad (login) en el "contexto de la aplicación" es decir, en el archivo de configuración o en el contenedor de beans.
// Forzosamente debemos tener esta clase, a pesar de que queramos usar otro login, como es nuestro caso.
// Con este código, entonces, provocamos que se genere una interfaz de login.
// La configuración del login, ya se realiza en el archivo de configuración de Spring Security (SecurityAppConfig).
// Protege todo, excepto si se realizan excepciones en el archivo de configuración de Spring Security.

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

}

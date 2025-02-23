package com.cursos.api.authorization_server.config.security;

import com.cursos.api.authorization_server.exception.ObjectNotFoundException;
import com.cursos.api.authorization_server.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//EN ESTA CLASE SE REGISTRAN BEANS QUE SERÁN USADOS INTERNAMENTE POR SPRING SECURITY. SE CREAN Y REGISTRAN DESDE QUE SE LEVANTA EL SERVUDOR, INCLUSO ANTES DE LA CREACION DE LOS FILTROS.
//PRINCIPALMENTE SIRVE PARA VALIDAR CREDENCIALES DEL USUARIO.
//SI USAMOS OAUTH2 LA RESPONSABILIDAD DE VALIDAR LA AUTENTICACION DEL USUARIO ES SOLAMENTE DEL AUTHORIZATION SERVER. YA NO DE LA APLICACION.
@Configuration
public class SecurityBeanInjector {

    @Autowired
    private UserRepository userRepository;


    @Bean
    public AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider authenticationStrategy=new DaoAuthenticationProvider();           //DaoAuthenticationProvider ES EL TIPO DE PROVEEDOR QUE USAREMOS: VALIDA USUARIOS ALMACENADOS EN UNA BASE DE DATOS(O CUQLUIER ORIGEN) A TRAVÉS DE UN UserDetailsService, QUIEN CARGA LOS DETALLES DEL USUARIO Y LUEGO USA UN PasswordEncoder PARA VERIFICAR LA CONTRASEÑA.
        System.out.println("Este es el authenticationStrategy en authenticationProvider: "+authenticationStrategy);     //OTROS TIPOS DE PROVEDORES SON: LdapAuthenticationProvider(REALIZA BUSQUEDAS EN SERVIDORES LDAP. ELLOS MANEJAN SU PROPIO PasswordEncoder), JwtAuthenticationProvider, AnonymousAuthenticationProvider, PreAuthenticatedAuthenticationProvider(MANEJA AUTENTICACIONES REALIZADAS PREVIAMENTE COMO SSO,POR LO QUE UN PasswordEncoder YA NO SERIA NECESARIO), OauthAuthenticationProvider(MANEJA AUTENTICACIONES REALIZADAS MEDIANTE OAUTH2:Facebook, Google, etc.)
                                                                                                    //SE PUEDEN CONFIGURAR VARIOS PROVEEDORES.
        authenticationStrategy.setPasswordEncoder(passwordEncoder());//SE ESTABLECE EL COMPONENTE ENCARGADO DE HASHEAR Y VERIFICAR LAS CONNTRASEÑAS. SE HARÁ A TRAVES DE BCryptPasswordEncoder.
        authenticationStrategy.setUserDetailsService(userDetailsService());//SE ESTABLECE EL COMPONENTE ENCARGADO DE CARGAR LOS DETALLES DEL USUARIO(nombre, roles, contraseña codificada, etc) DESDE EL ORIGEN DE DATOS COMO UNA BASE DE DATOS. SE REALIZARÁ UNA BUSQUEDA NORMAL CON  JpaRepository

        System.out.println("Este es el authenticationStrategy en authenticationProvider despues de seteos: "+authenticationStrategy);

        return authenticationStrategy;

    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        System.out.println("Entrando en passwordEncoder");
        return new BCryptPasswordEncoder();  //CUANDO UN USUARIO INGRESA SU CONTRASEÑA, LA HASHEA Y LA COMPARA CON EL HASH ALMACENADO EN LA BASE DE DATOS COMO CONTRASEÑA. USA EL ALGORITMO BCrypt. RESISTE ATAQUES DE FUERZA BRUTA. ES EL MAS SEGURO AL DIA DE HOY.
    }                                       //HAY OTROS TIPOS: Pbkdf2PasswordEncoder, Argon2PasswordEncoder, SCryptPasswordEncoder, NoPasswordEncoder, StandardPasswordEncoder


    //METODO ESENCIAL. BUSCA AL USUARIO EN LA BASE DE DATOS CUANDO INTENTA HACER LOGIN.
    @Bean
    public UserDetailsService userDetailsService(){

        System.out.println("Entrando en userDetailsService");
        return ((username) -> {
            return userRepository.findByUsername(username).orElseThrow(()->new ObjectNotFoundException("User not found with username "+username));
        });
    }



}


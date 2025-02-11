package com.cursos.api.spring_security.config.security;

import com.cursos.api.spring_security.exceptions.ObjectNotFoundException;
import com.cursos.api.spring_security.persistence.repository.security.UserRepository;
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

@Configuration
public class SecurityBeanInjector {

    @Autowired
    private UserRepository userRepository;

    /*@Autowired
    private AuthenticationConfiguration authenticationConfiguration;*/


    //AuthenticationManager ES UNA INTERFAZ ENCARGADA DE MANEJAR LA AUTENTICACION DE LOS USUARIOS. SU FUNCION ES ESENCIAL: ES LA QUE VERIFICA LAS CREDENCIALES PROPORCIONADAS POR UN USUARIO.
    //SI LAS CREDENCIALES COINCIDEN DEVUELVE UN OBJETO authentication; SI NO, DEVUELVE UN OBJETO AuthenticationException.
    //EN ESTE MÉTOD LO UNICO QUE ESTAMOS HACIENDO ES OBTENER EL AuthenticationManager CONFIGURADO.
    //AuthenticationManager DELEGA SU TAREA A AuthenticationProvider.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{

        System.out.println("Este es el authenticationConfiguration.getAuthenticationManager en authenticationManager: "+authenticationConfiguration.getAuthenticationManager());
        System.out.println("Este es el authenticationConfiguration en authenticationManager: "+authenticationConfiguration);

       return authenticationConfiguration.getAuthenticationManager();   //EL OBJETO RETORNADO SIRVE PARA VALIDAR(AUTENTICAR) A USUARIOS YA RESGITRADOS. SE USA DENTRO DEL METODO Login AuthenticationService.
    }

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

        return new BCryptPasswordEncoder();  //CUANDO UN USUARIO INGRESA SU CONTRASEÑA, LA HASHEA Y LA COMPARA CON EL HASH ALMACENADO EN LA BASE DE DATOS COMO CONTRASEÑA. USA EL ALGORITMO BCrypt. RESISTE ATAQUES DE FUERZA BRUTA. ES EL MAS SEGURO AL DIA DE HOY.
    }                                       //HAY OTROS TIPOS: Pbkdf2PasswordEncoder, Argon2PasswordEncoder, SCryptPasswordEncoder, NoPasswordEncoder, StandardPasswordEncoder

    @Bean
    public UserDetailsService userDetailsService(){

        return ((username) -> {
            return userRepository.findByUsername(username).orElseThrow(()->new ObjectNotFoundException("User not found with username "+username));
        });
    }



}


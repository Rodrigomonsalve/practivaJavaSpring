package com.cursos.api.authorization_server.config.security;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

//ESTUDIAR POR QUÉ @Component ESTA AFECTANDO
//@Component

// Aqui se crean 2 filtros. Uno para manejar solictudes  a oauth y otra al /login.
// Cuando creas varios filtros, debes especificar el orden en que se evaluarán.
@Configuration
public class AuthorizationSecurityConfig {


    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("Ingresando al primer filtro");
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http); // Se crea la configuracion por defecto de un servidor de autoriacion. Crea endpoints como /oauth2/token, /oauth2/authorize, /oauth/jwks. Se autoriza la creación de access_token.
            http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)   //Habilita OpenID Connect(OIDC). Se usa en el scope: "openid READ_ALL_PRODUCTS". Genera un id_token. Sirve para autenticar usuarios. Oauth2 sólo sirve para autenticar  clientes(aplicaciones). Oauth2 genera access_token.
                .oidc(Customizer.withDefaults());
        //ESTUDIAR EXCEPCIONES
        http.exceptionHandling(exceptionConfig -> {
            exceptionConfig.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));  //Si un usuario no autennticado inteta acceder, será redirigido a /login
        });

        http.oauth2ResourceServer(oauthResourceServerConfig->{
            oauthResourceServerConfig.jwt(Customizer.withDefaults());
        });

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("Ingresando al segundo filtro");
        http.authorizeHttpRequests(authConfig->{
            authConfig.requestMatchers("/login").permitAll(); //Permite el acceso al login a todos.
            authConfig.anyRequest().authenticated(); //Todas las demas solicitudes requieren autenticacion.
        });
       // http.formLogin(Customizer.withDefaults());
        http.formLogin(formLoginConfig -> formLoginConfig.loginPage("/login").permitAll());  //AQUI SE ESTABLECE CUÁL VA A SER EL LOGIN. formLogin HABILITA LA AUTENTICACION BASADA EN FORMULARIO Y PROVOCA QUE CUALQUIER PETICION DESDE EL /login SEA INTERCEPTADA POR post /login, AUNQUE NO LO TENGAMOS EN NUESTRO CONTROLADOR.

        return http.build();
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
                .issuer("http://localhost:9595/authorization-server")  //Se establece la url que va a emitir  access_token. Sólo establece la identidad del servidor de authozation
                .build();
    }


    private KeyPair generateRsaKey() {

        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        }
        catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

}

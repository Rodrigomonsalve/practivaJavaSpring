package com.cursos.api.spring_security;

import com.cursos.api.spring_security.filter.JwtAuthenticationFilter;
import com.cursos.api.spring_security.persistence.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) //Sirve para habilitar la autorizacion "con base en metodos". prePostEnabled habilita las anotaciones @PreAuthorize y @PostAuthorize
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider daoAuthProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                //filterChain se crea desde que el servidor es encendido. Este metodo se ejecuta en ese momento.
                //SecurityFilterChain es una "cadena de filtros" de seguridad. Aqui vas poniendo las reglas y los filtros personalizados. De todas formas Spring Security ya cuenta con sus propios filtros que se ejecutarán de forma automática al establecer el filterchain(UsernamePasswordAuthenticationFilter, LogoutFilter, DefaultLoginPageGenaratingFilter, etc).
                SecurityFilterChain filterChain= http
                .csrf(csrfConfig-> csrfConfig.disable())
                .sessionManagement(sessMagConfig->sessMagConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(daoAuthProvider)
                        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) //Asi se añade un filtro personalizado al "filter chain". En este caso, estamos diciendo que nuestro filtro personalizdo se eejecute antes que el filtro UsernamePasswordAuthenticationFilter, si ya contamos con jwt vigente. Este filtro consiste en pedir usuario y contraseña al usuario para que se loguee. Esta tamibien el metodo .addFilterAfter y .addFilter.
//                .authorizeHttpRequests(authReqConfig->{   //PARA CONFIGURAR LA AUTORIZACION CON BASE EN COINCIDENCIAS DE URLS.
//                    //buildRequestMatcher(authReqConfig);    ----> LLAMADO AL METODO EN DONDE CONFIGURAMOS LA AUTORIZACION CON BASE EN COINCIDENCIAS DE URL.
//                })
                .build();

                System.out.println("Este es el objeto filterChain(HttpSecurityConfig): "+filterChain);  //DefaultSecurityFilterChain [RequestMatcher=any request, Filters=[org.springframework.security.web.session.DisableEncodeUrlFilter@6de2344a, org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@577dac16, org.springframework.security.web.context.SecurityContextHolderFilter@57c7ec30, org.springframework.security.web.header.HeaderWriterFilter@45649467, org.springframework.security.web.authentication.logout.LogoutFilter@53945f9c,
                                                                                                        // com.cursos.api.spring_security.filter.JwtAuthenticationFilter@1802f60c, org.springframework.security.web.savedrequest.RequestCacheAwareFilter@2c8c42c, org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@47f8374, org.springframework.security.web.authentication.AnonymousAuthenticationFilter@18ed9480, org.springframework.security.web.session.SessionManagementFilter@e356940, org.springframework.security.web.access.ExceptionTranslationFilter@6fd07a56, org.springframework.security.web.access.intercept.AuthorizationFilter@2723b0d0]]

                return filterChain;
    }

    //Este metodo sirve para desproteger determinados endpoints. Por defecto, todos el proyecto queda protegido al incluir la dependencia de spring-security, habilitándose un formulario de login por defecto.
    //El siguiente metodo sirve para autorizar "con base en coincidencias de url" y funciona perfectamente. En caso de que el usuario no este autorizado, devolverá un error 403Forbidden.
    
    //Ademas, existe la forma de autorizar "con base en metodos". Son excluyentes entre sí. Se realiza con anotaciones en los metodos controladores.
    private static void buildRequestMatcher(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authReqConfig) {

//        /*
//            Autorizacion de endpoints de productos(basado en coincidencias de urls). El usuario ya pasó la autenticacion:
//        */
//
//                //COMO PODEMOS VER, PODEMOS CREAR AUTORIZACIONES BASADAS EN ROLES O EN AUTHORITIES. HAY QUE RECORDAR QUE LOS USUARIOS SE DIERON DE ALTA CON ROLES (administrador, asistente de administrador y cliente). CADA ROL SE CONFORMA DE MUCHOS "authorities".
//        authReqConfig.requestMatchers(HttpMethod.GET,"/products")                           //Significa que si el usuario fue dado de alta como administrador o asistente de administrador, tendrá acceso a la funcionalidd del endpoint /products
//                        .hasAnyRole(Role.ADMINISTRATOR.name(),Role.ASSISTANT_ADMINISTRATOR.name());  //autorizacion basada en roles.
//                //.hasAuthority(RolePermission.READ_ALL_PRODUCTS.name());                            //autorizacion basada en authorities. Sólo debes usar una de las 2.
//
//        //authReqConfig.requestMatchers(HttpMethod.GET,"/products/{productId}")
//        authReqConfig.requestMatchers(String.valueOf(RegexRequestMatcher.regexMatcher(HttpMethod.GET)),"/products/[0-9]*")//Es otra forma de crear una autorizacion con regex.
//                .hasAnyRole(Role.ADMINISTRATOR.name(),Role.ASSISTANT_ADMINISTRATOR.name());
//                //.hasAuthority(RolePermission.READ_ONE_PRODUCT.name());
//
//        authReqConfig.requestMatchers(HttpMethod.POST,"/products")
//                .hasAnyRole(Role.ADMINISTRATOR.name());
//                //.hasAuthority(RolePermission.CREATE_ONE_PRODUCT.name());
//
//        authReqConfig.requestMatchers(HttpMethod.PUT,"/products/{productId}")
//                .hasAnyRole(Role.ADMINISTRATOR.name(),Role.ASSISTANT_ADMINISTRATOR.name());
//                //.hasAuthority(RolePermission.UPDATE_ONE_PRODUCT.name());
//
//        authReqConfig.requestMatchers(HttpMethod.POST,"/products/{productId}/disable")
//                .hasAnyRole(Role.ADMINISTRATOR.name());
//               //.hasAuthority(RolePermission.DISABLE_ONE_PRODUCT.name());
//
//                    /*
//                    Autorizacion de endpoints de categorias(basado en coincidencias de urls). El usuario ya pasó la autenticacion:
//                     */
//
//        authReqConfig.requestMatchers(HttpMethod.GET,"/categories")
//                .hasAnyRole(Role.ADMINISTRATOR.name(),Role.ASSISTANT_ADMINISTRATOR.name());
//                //.hasAuthority(RolePermission.READ_ALL_CATEGORIES.name());
//
//        authReqConfig.requestMatchers(HttpMethod.GET,"/categories/{categoryId}")
//                .hasAnyRole(Role.ADMINISTRATOR.name(),Role.ASSISTANT_ADMINISTRATOR.name());
//                //.hasAuthority(RolePermission.READ_ONE_CATEGORY.name());
//
//        authReqConfig.requestMatchers(HttpMethod.POST,"/categories")
//                .hasAnyRole(Role.ADMINISTRATOR.name());
//                //.hasAuthority(RolePermission.CREATE_ONE_CATEGORY.name());
//
//        authReqConfig.requestMatchers(HttpMethod.PUT,"/categories/{categoryId}/disable")
//                .hasAnyRole(Role.ADMINISTRATOR.name());
//                //.hasAuthority(RolePermission.UPDATE_ONE_CATEGORY.name());
//
//        authReqConfig.requestMatchers(HttpMethod.POST,"/categories")
//                .hasAnyRole(Role.ADMINISTRATOR.name(),Role.ASSISTANT_ADMINISTRATOR.name(),Role.CUSTOMER.name());
//                //.hasAuthority(RolePermission.DISABLE_ONE_CATEGORY.name());
//
//        authReqConfig.requestMatchers(HttpMethod.GET,"/auth/profile")
//                .hasAnyRole(Role.ADMINISTRATOR.name(),Role.ASSISTANT_ADMINISTRATOR.name());
//                //.hasAuthority(RolePermission.READ_MY_PROFILE.name());

        /*Autorizacion de Endpoints publicos:*/

        authReqConfig.requestMatchers(HttpMethod.POST, "/customers").permitAll();
        authReqConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
        authReqConfig.requestMatchers(HttpMethod.GET, "/auth/validate-token").permitAll();

        authReqConfig.anyRequest().authenticated();//Todos lo que no este arriba, sólo requiere autenticacion. Todavia la autorizacion no importa.
    }
}

package com.cursos.api.spring_security.authorization;

import com.cursos.api.spring_security.exceptions.ObjectNotFoundException;
import com.cursos.api.spring_security.persistence.entity.security.Operation;
import com.cursos.api.spring_security.persistence.entity.security.User;
import com.cursos.api.spring_security.persistence.repository.security.OperationRepository;
import com.cursos.api.spring_security.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


//Toda esta clase sustituye a la autorizacion basada en coincidencias de url y a la basada en metodos. Ninguno de las 3 formas se debe combinar con otra. Debe ser agregado al filterchain.
//Se basa ya en usuarios, roles y permisos registrados en base de datos.

@Component
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {


    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private UserService userService;


    //La finalidad de este metodo(es el principal de esta clase) es saber si determinado recurso está permitido para el usuario solicitante o esta prohibido.
    //Devuelve un objeto de tipo AuthorizationDecision que encapsula el resultado de la evaluación de la autorización de un usuario para una solicitud determinada.
    //Para determinar lo anterior primero debemos saber si el recurso es público o privado. Si es público ya devuelve "true" en el objeto de tipo AuthorizationDecision; si no, se evalúa si tiene permisos(authorities). Para conocer esto, primero obtenemos path que intenta visitar, metodo usado(POST,GET,ETC) e información del usuario autenticado.
    @Override                                                //"authentication" trae los datos de autenticación del usuario activo, como nombre y authorities.
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext requestContext) {


        HttpServletRequest request =requestContext.getRequest();
        System.out.println("requestContext en check: "+requestContext); // org.springframework.security.web.access.intercept.RequestAuthorizationContext@9d4d103
        System.out.println("request en check: "+request); //    SecurityContextHolderAwareRequestWrapper[ org.springframework.security.web.header.HeaderWriterFilter$HeaderWriterRequest@25798e09]
        System.out.println("request.getContextPath en check: "+request.getContextPath());           //    /api/v1
        System.out.println("requestContext.getVariables en check: "+requestContext.getVariables()); //     {}
        System.out.println("URL en check :"+request.getRequestURL());                               //     http://localhost:9191/api/v1/products    //Es el endpoint al que se intenta acceder.
        System.out.println("URI en check :"+request.getRequestURI());                               //      /api/v1/products                            Es mejor trabajar con la URI, porque el hostname puede cambiar dependiendo el ambiente.

        String url=extractUrl(request);
        System.out.println("extractUrl en check :"+url); // /products

        String httpMethod=request.getMethod();
        System.out.println("httpMethod en check :"+httpMethod); //  GET

        //1) EN ESTE PUNTO SE REALIZA LA PRIMERA CONSULTA A LA BD.
        //Hibernate: select o1_0.id,o1_0.http_method,o1_0.module_id,o1_0.name,o1_0.path,o1_0.permit_all from operation o1_0 where o1_0.permit_all=true
        //Hibernate: select m1_0.id,m1_0.base_path,m1_0.name from module m1_0 where m1_0.id=?
        //Hibernate: select m1_0.id,m1_0.base_path,m1_0.name from module m1_0 where m1_0.id=?

        boolean isPublic=isPublic(url, httpMethod);
        System.out.println("isPublic en check :"+isPublic); //  false

        //2) EN ESTE PUNTO SE REALIZA LA SEGUNDA CONSULTA A LA BD.
        //Hibernate: select u1_0.id,u1_0.name,u1_0.password,u1_0.role_id,u1_0.username from "user" u1_0 where u1_0.username=?
        //Hibernate: select r1_0.id,r1_0.name,p1_0.role_id,p1_0.id,o1_0.id,o1_0.http_method,m1_0.id,m1_0.base_path,m1_0.name,o1_0.name,o1_0.path,o1_0.permit_all from role r1_0 left join granted_permissions p1_0 on r1_0.id=p1_0.role_id left join operation o1_0 on o1_0.id=p1_0.operation_id left join module m1_0 on m1_0.id=o1_0.module_id where r1_0.id=?


        if(isPublic){
            return new AuthorizationDecision(true);
        }

        //NO SE EJECUTA SI SÓLO NOS LOGUEAMOS:
        boolean isGranted =isGranted(url,httpMethod,authentication.get());
        System.out.println("isGranted en check :"+isGranted);// true        //    /true
        System.out.println("authentication en check :"+authentication); //  org.springframework.security.web.access.intercept.AuthorizationFilter$$Lambda$1664/0x0000024f1faef550@2641b70f
        System.out.println("authentication.get en check :"+authentication.get()); //    UsernamePasswordAuthenticationToken [Principal=fperez, Credentials=[PROTECTED], Authenticated=true, Details=WebAuthenticationDetails [RemoteIpAddress=0:0:0:0:0:0:0:1, SessionId=null], Granted Authorities=[READ_ALL_PRODUCTS, READ_ONE_PRODUCT, UPDATE_ONE_PRODUCT, READ_ALL_CATEGORIES, READ_ONE_CATEGORY, UPDATE_ONE_CATEGORY, READ_MY_PROFILE, ROLE_ASSISTANT_ADMINISTRATOR]]

        //4)EN ESTE PUNTO SE REALIZA UNA CUARTA CONSULTA A BD:
        //Hibernate: select p1_0.id,p1_0.category_id,p1_0.name,p1_0.price,p1_0.status from product p1_0 offset ? rows fetch first ? rows only
        //Hibernate: select c1_0.id,c1_0.name,c1_0.status from category c1_0 where c1_0.id=?
        //Hibernate: select c1_0.id,c1_0.name,c1_0.status from category c1_0 where c1_0.id=?
        //Hibernate: select c1_0.id,c1_0.name,c1_0.status from category c1_0 where c1_0.id=?
        //Hibernate: select c1_0.id,c1_0.name,c1_0.status from category c1_0 where c1_0.id=?

        return new AuthorizationDecision(isGranted);
    }


    private boolean isGranted(String url, String httpMethod, Authentication authentication) {

        if(authentication==null || !(authentication instanceof UsernamePasswordAuthenticationToken)){

            throw new AuthenticationCredentialsNotFoundException("User not logged in");
        }

        System.out.println("authentication en isGranted :"+authentication); //  UsernamePasswordAuthenticationToken [Principal=fperez, Credentials=[PROTECTED], Authenticated=true, Details=WebAuthenticationDetails [RemoteIpAddress=0:0:0:0:0:0:0:1, SessionId=null], Granted Authorities=[READ_ALL_PRODUCTS, READ_ONE_PRODUCT, UPDATE_ONE_PRODUCT, READ_ALL_CATEGORIES, READ_ONE_CATEGORY, UPDATE_ONE_CATEGORY, READ_MY_PROFILE, ROLE_ASSISTANT_ADMINISTRATOR]]

        UsernamePasswordAuthenticationToken authToken=(UsernamePasswordAuthenticationToken)authentication;
        System.out.println("authToken en isGranted :"+authToken); //    UsernamePasswordAuthenticationToken [Principal=fperez, Credentials=[PROTECTED], Authenticated=true, Details=WebAuthenticationDetails [RemoteIpAddress=0:0:0:0:0:0:0:1, SessionId=null], Granted Authorities=[READ_ALL_PRODUCTS, READ_ONE_PRODUCT, UPDATE_ONE_PRODUCT, READ_ALL_CATEGORIES, READ_ONE_CATEGORY, UPDATE_ONE_CATEGORY, READ_MY_PROFILE, ROLE_ASSISTANT_ADMINISTRATOR]]

        List<Operation> operations= obtainedOperations(authentication);
        System.out.println("operations en isGranted :"+operations); //  [com.cursos.api.spring_security.persistence.entity.security.Operation@3576e6ce, com.cursos.api.spring_security.persistence.entity.security.Operation@23a7d5ed, com.cursos.api.spring_security.persistence.entity.security.Operation@6ddbc09b, com.cursos.api.spring_security.persistence.entity.security.Operation@417b3635, com.cursos.api.spring_security.persistence.entity.security.Operation@5a9f4e7e, com.cursos.api.spring_security.persistence.entity.security.Operation@31ad38bd, com.cursos.api.spring_security.persistence.entity.security.Operation@61bd31ad]

        boolean isGranted=operations.stream().anyMatch(getOperationPredicate(url, httpMethod));
        System.out.println("isGranted en isGranted :"+isGranted); //    true    //  true

        return isGranted;
    }


    //ESTUDIAR Predicate
    //Este metodo tiene 2 finalidades, dependiendo qué metodo lo invoque:
    // 1)Saber si el endpoint al que se está intentando acceder está protegido. Esto se logra comparando todas las urls publicas con el endpoint al que estamos intentando acceder.
    // 2)Saber si el usuario tiene permisos para acceder a una url(endpoint) protegido. Esto se logra comparando las urls a las que tiene acceso el usuario segun la tabla operacion, con la url a la que esta intentando acceder.
    private static Predicate<Operation> getOperationPredicate(String url, String httpMethod) {

        return operation -> {  //operation trae la información de las urls que ya sabemos son públicas o las urls a las que el usuario tiene acceso.

            String basePath = operation.getModule().getBasePath();
            System.out.println("basePath en getOperationPredicate :"+basePath); //  /customers  //  /auth   //  /auth       ------    //                               /products

            //ESTUDIAR:
            Pattern pattern = Pattern.compile(basePath.concat(operation.getPath()));
            System.out.println("pattern en getOperationPredicate :"+pattern);   // /customers   // /auth/authenticate   //  /auth/validate-token        -------    //  /products

            Matcher matcher = pattern.matcher(url);
            System.out.println("matcher en getOperationPredicate :"+matcher); //    java.util.regex.Matcher[pattern=/customers region=0,9 lastmatch=]   //  java.util.regex.Matcher[pattern=/auth/authenticate region=0,9 lastmatch=]   // java.util.regex.Matcher[pattern=/auth/validate-token region=0,9 lastmatch=]          ------       //    java.util.regex.Matcher[pattern=/products region=0,9 lastmatch=]

            System.out.println("matcher.matches en getOperationPredicate :"+matcher.matches()); //  false   // false    // false        ------          //  true
            System.out.println("operation.getHttpMethod().equals(httpMethod) en getOperationPredicate :"+operation.getHttpMethod().equals(httpMethod)); // false    //false     //  true        -----           //  true        //true

            return matcher.matches() && operation.getHttpMethod().equals(httpMethod);
        };
    }


    private List<Operation> obtainedOperations(Authentication authentication) {

        UsernamePasswordAuthenticationToken authToken=(UsernamePasswordAuthenticationToken) authentication;
        System.out.println("authToken en obtainedOperations :"+authToken); //   UsernamePasswordAuthenticationToken [Principal=fperez, Credentials=[PROTECTED], Authenticated=true, Details=WebAuthenticationDetails [RemoteIpAddress=0:0:0:0:0:0:0:1, SessionId=null], Granted Authorities=[READ_ALL_PRODUCTS, READ_ONE_PRODUCT, UPDATE_ONE_PRODUCT, READ_ALL_CATEGORIES, READ_ONE_CATEGORY, UPDATE_ONE_CATEGORY, READ_MY_PROFILE, ROLE_ASSISTANT_ADMINISTRATOR]]

        String username=(String) authToken.getPrincipal();
        System.out.println("username en obtainedOperations :"+username); // fperez

        //3)EN ESTE PUNTO SE REALIZA OTRA CONSULTA A BD:
        //Hibernate: select u1_0.id,u1_0.name,u1_0.password,u1_0.role_id,u1_0.username from "user" u1_0 where u1_0.username=?
        //Hibernate: select r1_0.id,r1_0.name,p1_0.role_id,p1_0.id,o1_0.id,o1_0.http_method,m1_0.id,m1_0.base_path,m1_0.name,o1_0.name,o1_0.path,o1_0.permit_all from role r1_0 left join granted_permissions p1_0 on r1_0.id=p1_0.role_id left join operation o1_0 on o1_0.id=p1_0.operation_id left join module m1_0 on m1_0.id=o1_0.module_id where r1_0.id=?

        User user=userService.findOneByUsername(username).orElseThrow(()->new ObjectNotFoundException("User not found"));
        System.out.println("user en obtainedOperations :"+user); // com.cursos.api.spring_security.persistence.entity.security.User@752213bf

       return user.getRole().getPermissions().stream().map(grantedPermissions -> grantedPermissions.getOperation()).collect(Collectors.toList());
    }


    private boolean isPublic(String url, String httpMethod) {

        List<Operation> publicAccessEndpoints=operationRepository.findByPublicAccess(); // Obtiene todas las Operaciones en las que el campo permit_all sea true.
        for(Operation op : publicAccessEndpoints){
            System.out.println("Lista de operations(name): "+op.getName());
            System.out.println("Lista de operations(path): "+op.getPath());
            System.out.println("Lista de operations(method): "+op.getHttpMethod());
            System.out.println("Lista de operations(module): "+op.getModule().getBasePath());
        }
        System.out.println("publicAccessEndpoints en isPublic :"+publicAccessEndpoints); // [com.cursos.api.spring_security.persistence.entity.security.Operation@7434c935, com.cursos.api.spring_security.persistence.entity.security.Operation@1da68809, com.cursos.api.spring_security.persistence.entity.security.Operation@f4e467a]

        boolean isPublic=publicAccessEndpoints.stream().anyMatch(getOperationPredicate(url,httpMethod));
        System.out.println("publicAccessEndpoints.stream en isPublic :"+publicAccessEndpoints.stream()); // java.util.stream.ReferencePipeline$Head@2bbbe11a
        System.out.println("isPublic en isPublic :"+isPublic); //   false

        return isPublic;
    }


    private String extractUrl(HttpServletRequest request) {

        String contextPath=request.getContextPath();
        System.out.println("contextPath en extractUrl :"+contextPath); //   /api/v1

        String url=request.getRequestURI();
        System.out.println("url en extractUrl :"+url); //   /api/v1/products

        url=url.replace(contextPath,"");  //ESTUDIAR
        System.out.println("url2 en extractUrl :"+url); //  /products

        return url;
    }
}

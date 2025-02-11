package com.cursos.api.spring_security.filter;

import com.cursos.api.spring_security.exceptions.ObjectNotFoundException;
import com.cursos.api.spring_security.persistence.entity.security.JwtToken;
import com.cursos.api.spring_security.persistence.entity.security.User;
import com.cursos.api.spring_security.persistence.repository.security.JwtTokenRepository;
import com.cursos.api.spring_security.service.UserService;
import com.cursos.api.spring_security.service.auth.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

//Un filtro en spring security, al igual que en JEE, es un metodo que intercepta y procesa solicitudes http antes de que lleguen al controlador o servlet.
//Por ejmplo, intercepta
//Se debe definir dentro del metodo securityFilterChain, que esta en la clase HttpSecurityConfig
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {   //La clase OncePerRequestFilter sirve para que el filtro se ejecute sólo una vez por cada solicitud HTTP.

    @Autowired
    private JwtService jwtService;

    @Autowired
    private JwtTokenRepository jwtRepository;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("ENTRO EN EL FILTRO JwtAuthenticationFilter");


                //Lo que sigue sirve para interceptar el jwt que viaja en cada solicitud http y VALIDARLO. Se ejecuta con cada solicitud. Viaja en la cabecera Authorization. Hay que recordar que es un filtro. Se debe colocar en el filterChain.
                //Es el filtro que AUTENTICA al usuario(valida su jwt). Si es correcto, contiua con el flujo.

//        String authorizationHeader=request.getHeader("Authorization");
//        System.out.println("Este el objeto request(JwtAuthenticationFilter): "+request);
//        System.out.println("Este el objeto response(JwtAuthenticationFilter): "+response);
//        if(!StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")){  //"Bearer" es la palabra con que siempre va iniciar el campo del jwt.
//            filterChain.doFilter(request,response);
//            return;
//        }
//
//        String jwt=authorizationHeader.split(" ")[1];

        //ESTUDIAR StrinngUtils Y return vacio
        String jwt=jwtService.extractJwtFromRequest(request);  //Se guarda el jwt enviado por el usuario.
        if(jwt==null || !StringUtils.hasText(jwt)){            //Si no hay jwt ya no se ejecuta el demas metodo.
            filterChain.doFilter(request,response);
            return;
        }

                Optional<JwtToken> token=jwtRepository.findByToken(jwt); //Se busca el jwt en la bd.
                boolean isValid=validateToken(token);  //Esta validacion es necesaria, porque, hay que recordar, cuando hacemos logout, el jwt no se elimina, sino que sigue en BD pero con el atributo isValid en false. Debemos validar esto en cada solicitud. Si esta en false, ya no se podrá continuar con la solicitud.

                if(!isValid){                           // Si el token no es vàlido(se hizo logout o ya expiró), ya no se contiua con el demas código de este metodo.)
                    filterChain.doFilter(request,response);
                    return;
                }

        //En esta linea se valida el jwt. Hay que recordar que jwt es un elemento externo a spring-security y por sí sólo no significa nada para spring. Entonces hay que hacer 2 coas:
        //1) Validar el token, lo cual se hace en esta linea. Se invoca una cadena de métodos.
        //2) Registrar esta validación en spring.
        String username=jwtService.extractUsername(jwt);

        User user =userService.findOneByUsername(username)
                .orElseThrow(()->new ObjectNotFoundException("User not found"));

        // Las siguientes lineas nos ayudan a registrar la autenticación del usuario en el SecurityContextHolder.
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(
                username,null, user.getAuthorities()
        );
        System.out.println("Este es el objeto authenticationToken(JwtAuthenticationFilter): "+authenticationToken);//UsernamePasswordAuthenticationToken [Principal=lmarquez, Credentials=[PROTECTED], Authenticated=true, Details=null, Granted Authorities=[READ_MY_PROFILE]]
        authenticationToken.setDetails(new WebAuthenticationDetails(request));

        System.out.println("Este es el objeto authenticationToken despues de setDetails(JwtAuthenticationFilter): "+authenticationToken);//UsernamePasswordAuthenticationToken [Principal=lmarquez, Credentials=[PROTECTED], Authenticated=true, Details=WebAuthenticationDetails [RemoteIpAddress=0:0:0:0:0:0:0:1, SessionId=null], Granted Authorities=[READ_MY_PROFILE]]
        //La autenticacion correcta se guarda en SecurityContextHolder. Puede ser usado en cualquier clase para obtener los datos del usuario que queramos. Ahi se guardan.
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request,response);

    }

    private boolean validateToken(Optional<JwtToken> optionalJwtToken) {

        //ESTUDIAR isPresent
        if(!optionalJwtToken.isPresent()){   //Si no llega token generado por el usuario.
            System.out.println("Token no existe o no fue generado en nuestro sistema");
            return false;
        }

        //ESTUDIAR Date
        JwtToken token=optionalJwtToken.get();  //Se guarda el jwt.
        Date now= new Date(System.currentTimeMillis());

        boolean isValid=token.isValid() && token.getExpiration().after(now);  //Si el token tiene el atributo isValid en true y no ha expirado, se guarda un "true".
        if(!isValid){
            System.out.println("Token invalido");
            updateTokenStatus(token);
        }

        return isValid;

    }

    private void updateTokenStatus(JwtToken token) {

        token.setValid(false);
        jwtRepository.save(token);
    }
}

package com.cursos.api.spring_security.filter;

import com.cursos.api.spring_security.exceptions.ObjectNotFoundException;
import com.cursos.api.spring_security.persistence.entity.User;
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

//Un filtro en spring security, al igual que en JEE, es un metodo que intercepta y procesa solicitudes http antes de que lleguen al controlador o servlet.
//Por ejmplo, intercepta
//Se debe definir dentro del metodo securityFilterChain, que esta en la clase HttpSecurityConfig
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {   //La clase OncePerRequestFilter sireve para que el filtro se ejecute sólo una vez por cada solicitud HTTP.

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("ENTRO EN EL FILTRO JwtAuthenticationFilter");


                //Lo que sigue sirve para interceptar el jwt que viaja en cada solicitud http. Viaja en la cabecera Authorization.
                //Es el filtro que AUTENTICA al usuario(valida su jwt). Si es correcto, contiua con el flujo.
        String authorizationHeader=request.getHeader("Authorization");
        System.out.println("Este el objeto request(JwtAuthenticationFilter): "+request);
        System.out.println("Este el objeto response(JwtAuthenticationFilter): "+response);
        if(!StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")){  //"Bearer" es la palabra con que siempre va iniciar el campo del jwt.
            filterChain.doFilter(request,response);
            return;
        }

        String jwt=authorizationHeader.split(" ")[1];

        String username=jwtService.extractUsername(jwt);

        User user =userService.findOneByUsername(username)
                .orElseThrow(()->new ObjectNotFoundException("User not found"));

        //Linea de codigo mas importante. Es la que autentica:
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
}

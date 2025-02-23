package com.cursos.api.authorization_server.config.security;

import org.apache.catalina.authenticator.SpnegoAuthenticator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomOAuth2TokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {

    @Override
    public void customize(JwtEncodingContext context) {

       Authentication authentication= context.getPrincipal();
       System.out.println("Este es el authentication de customize: "+authentication);
       System.out.println("Este es el authentication de customize: "+authentication);
        System.out.println("Este es el authentication.getAuthorities de customize: "+authentication.getAuthorities());

       String tokenType=context.getTokenType().getValue();
        System.out.println("Este es el tokenType de customize: "+tokenType);

       if(tokenType.equals("access_token")){

           List<String> authorities=authentication.getAuthorities().stream()
                           .map(GrantedAuthority::getAuthority)
                   .collect(Collectors.toList());
           context.getClaims().claim("permissions",authorities);

           System.out.println("Este es el authorities de customize: "+authorities);
       }

    }
}

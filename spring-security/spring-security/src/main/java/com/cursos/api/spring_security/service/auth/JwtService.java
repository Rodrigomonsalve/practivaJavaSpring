package com.cursos.api.spring_security.service.auth;

import com.cursos.api.spring_security.persistence.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${security.jwt.expiration-in-minutes}")
    private Long EXPIRATION_IN_MINUTES;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    //Metodo destinado a generar el jwt. Se genera despues de registrar a un nuevo usuario y pertenece a éste. Este metodo es invocado por AuthenticationService por su metodo registerOneCustomer, cuya finalidad es devolver en formato json, los datos del nuevo usuario registrado.
    public String generateToken(UserDetails user,  Map<String,Object> extraClaims) {
        Date issuedAt=new Date(System.currentTimeMillis());
        Date expiration=new Date((EXPIRATION_IN_MINUTES*60*1000)+issuedAt.getTime());

        //Todos los atributos del jwt se llaman "claims". Los extraClaims son datos adicionales que queremos pasar por el jwt, que podamos usar después para la autorización. Nunca debemos pasar el password, numeros de tarjetas ni ninguna otra informacion sensible.

        //CON VERSION 0.11.5 DE JJWT:
        /*String jwt= Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(generateKey(), SignatureAlgorithm.HS256)  //La firma del jwt(tercera parte) estará hashedada en hs256.
                .compact();*/

        //CON VERSION 0.12.3 DE JJWT:
        String jwt=Jwts.builder()
                .header()
                .type("JWT")
                .and()
                .subject(user.getUsername())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .claims(extraClaims)
                .signWith(generateKey(),Jwts.SIG.HS256)
                .compact();

        return jwt;
    }

//UNA VEZ DECODIFICADO EL JWT, SU HEADER SERÁ ALGO ASÍ:
    /* {
        "typ": "JWT",
        "alg": "HS256"  -->Es el tipo de algortimo que escogimos.
        }/*

//UNA VEZ DECODIFICADO EL JWT, SU PAYLOAD SERÁ ALGO ASÍ:
    {
        "role": "ROLE_CUSTOMER",        --->Estos 3 atributos son los extraClaims.
        "name": "Pepito Lopez",
        "authorities": [
            {
             "authority": "READ_MY_PROFILE"
            }
        ],
        "sub": "Pepito02",              --->Es el subject elegido
        "iat": 1738107440,              --->Fecha de creación
        "exp": 1738109240               --->Fecha de expiración
    }

//jwt va a ser algo como lo siguiente(dentro de la respuesta json):
    /*{
        "id": **,
            "name": "****",
            "username": "****",
            "role": "****",
            "jwt": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.
            eyJyb2xlIjoiUk9MRV9DVVNUT01FUiIsIm5hbWUiOiJQZXBpdG8gTG9wZXoiLCJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiUkVBRF9NWV9QUk9GSUxFIn1dLCJzdWIiOiJQZXBpdG8wMiIsImlhdCI6MTczODEwNzQ0MCwiZXhwIjoxNzM4MTA5MjQwfQ.
            wQgHIAeorDeEdjWAxBe4L8qibEhtyAAEbvSOENwIT4s"
    }*/
    //Al realizar pruebas conviene realizar pruebas con los jwts generados, en la pagina de JWT.

    //CON VERSION 0.11.5 DE JJWT:
    /*private Key generateKey() {

        byte[] passwordDecoded= Decoders.BASE64.decode(SECRET_KEY); //SECRET_KEY es una propiedad en el application.properties que tiene guardada una frase cuqluiera, pero codificada en base64. En este caso, la estamos decodificando.
        //byte[] key=SECRET_KEY.getBytes();

        return Keys.hmacShaKeyFor(passwordDecoded);
    }*/

    //CON VERSION 0.12.3 DE JJWT:
    private SecretKey generateKey() {

        byte[] passwordDecoded= Decoders.BASE64.decode(SECRET_KEY); //SECRET_KEY es una propiedad en el application.properties que tiene guardada una frase cuqluiera, pero codificada en base64. En este caso, la estamos decodificando.
        //byte[] key=SECRET_KEY.getBytes();

        return Keys.hmacShaKeyFor(passwordDecoded);
    }

    public String extractUsername(String jwt) {

        return extractAllClaims(jwt).getSubject();
    }

    private Claims extractAllClaims(String jwt) {

        //CON VERSION 0.11.5 DE JJWT:
        /*return Jwts.parserBuilder().setSigningKey(generateKey()).build()
                .parseClaimsJws(jwt).getBody();*/

        //CON VERSION 0.12.3 DE JJWT:
        return Jwts.parser().verifyWith(generateKey()).build()
                .parseSignedClaims(jwt).getPayload();
    }
}

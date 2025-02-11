package com.cursos.api.spring_security.service.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${security.jwt.expiration-in-minutes}")
    private Long EXPIRATION_IN_MINUTES;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    //Metodo destinado a generar el jwt. Se genera despues de registrar a un nuevo usuario y al hacer login. Este metodo es invocado por AuthenticationService por su metodo registerOneCustomer, cuya finalidad es devolver en formato json, los datos del nuevo usuario registrado, y login.
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

        return Keys.hmacShaKeyFor(passwordDecoded); //Genera una clave segura para firmar el jwt.
    }

    //Metodo invocado por el filtro, que sirve para validar el jwt del usuario que realiza la petición. Se ejecuta entonces, en cada petición.
    public String extractUsername(String jwt) {

        System.out.println("Este es el jwt en extractUsername"+extractAllClaims(jwt));
        return extractAllClaims(jwt).getSubject();
    }

    //Este metodo, en realidad, es quien nos ayuda a validar el jwt de cada peticion que envie el usuario. Es invocado por extractUsername.
    private Claims extractAllClaims(String jwt) {

        //CON VERSION 0.11.5 DE JJWT:
        /*return Jwts.parserBuilder().setSigningKey(generateKey()).build()
                .parseClaimsJws(jwt).getBody();*/

        //CON VERSION 0.12.3 DE JJWT:
        return Jwts.parser().verifyWith(generateKey()).build()   //AQUI SE VALIDA EL jwt USANDO LA CLAVE GENERADA CON generateKey(), Y LUEGO SE OBTIENE EL PAYLOAD DEL jwt.
                .parseSignedClaims(jwt).getPayload();
    }

    //ESTUDIAR startsWith y todos lo relacionado con request response.
    //Se ejecuta desde el filtro, antes de validar el jwt. para despues hacerlo.
    public String extractJwtFromRequest(HttpServletRequest request) {

        String authorizationHeader=request.getHeader("Authorization");  //Dame el  contenido de la cabecera "Authorization". En nuestro caso ahi viene el jwt.
        if(!StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")){  //"Bearer" es la palabra con que siempre va iniciar el campo del jwt. // Si no viene jwt  ya no se ejecuta lo que falta del metodo.
            return null;
        }

        //ESTUDIAR split
        return authorizationHeader.split(" ")[1]; //Lo que estamos haciendo es, primero convertir authorizationHeader en un array usando los espacios(" ") de este String como separador de los elementos del array. Luego extraemos el segundo valor([1]) que es nuestro jwt. ["Bearer", "hhuihue7738wjjw...."]
    }

    public Date extractExpiration(String jwt) {
        return extractAllClaims(jwt).getExpiration();
    }
}

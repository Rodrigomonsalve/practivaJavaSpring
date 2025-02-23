package com.cursos.api.authorization_server.mapper;

import com.cursos.api.authorization_server.entity.security.ClientApp;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Collectors;

//Para que el authrozation server pueda reconocer una aplicación, es necesario que se encuentre registrada en él. En esta clase se registra.
//Sin embbargo, para efectos de prueba, no vamos a colocar la url de la aplicación cliente sino la de oauthdeiger. Hay que recordar que oauthdebugger funge como nuestra aplicación cliente,y así no tenemos que desarrollar códigigo en nuestra apliccación cliente antes de probar nuestro authorization server.
// Hay que recordar que hay que desarrollar codigo para que se genere la url de autenticacion, pero en la aplicacion cliente.
// Al usar oauthdebugger no usamos para nada la url de la aplicacion cliente. Las credenciales que se validan en el login se realiza directamente a la base de datos desde el SecurityBeanInjector.
public class ClientAppMapper {

    //TODOS LO QUE SIGUE ES SÓLO PARA UN CLIENTE EN ESPECIIFICO.
    public static RegisteredClient toRegisteredClient(ClientApp clientApp){

        RegisteredClient client=RegisteredClient.withId(clientApp.getClientId())
                .clientId(clientApp.getClientId())              //--> Se registra un id de cliente(registramos "client")
                .clientSecret(clientApp.getClientSecret())      //--> Es la contraseña secreta del cliente. Esta contraseña debe estar tambien registrada en la aplicación en su application.properties. Si se usa sólo oauthdebbugger, no se necesita.
                .clientIdIssuedAt(new Date(System.currentTimeMillis()).toInstant())     //-->
                .clientAuthenticationMethods(clientAuthMethods -> {                     //--> Sirve para definir los métodos de autenticacion que puede usar el cliente. Oauth ya los tiene definidos (client_secret_basic, client_secret_post, private_key_jwt, none). Esto es la forma en que la aplicación se va a autenticar ante el authorization server.
                    clientApp.getClientAuthenticationMethods().stream()
                            .map(method->new ClientAuthenticationMethod(method))
                            //.forEach(clientAuthMethods.add(each));
                            .forEach(clientAuthMethods::add);
                })
                .authorizationGrantTypes(authorizationGrantTypes -> {                   //--> Sirve para definir los tipos autorizacion que el cliente puede usar. Oauth ya los tiene definidos (authorization_code, client_credentials, refresh_token, password). Esto es la forma en que la aplicacion obtiene un token de acceso.
                    clientApp.getAuthorizationGrantTypes().stream()
                            .map(grantType->new AuthorizationGrantType(grantType))
                            .forEach(authorizationGrantTypes::add);
                })
                .redirectUris(redirectUris->clientApp.getRedirectUris().stream().forEach(redirectUris::add))
                .scopes(scopes->clientApp.getScopes().stream().forEach(scopes::add))
                .tokenSettings(TokenSettings.builder()                                  //--> Sirve para establecer la duracion del access token.
                        .accessTokenTimeToLive(Duration.ofMinutes(clientApp.getDurationInMinutes()))
                        .refreshTokenTimeToLive(Duration.ofMinutes(clientApp.getDurationInMinutes()*4))
                        .build())
                .clientSettings(ClientSettings.builder()
                        .requireProofKey(clientApp.isRequiredProofKey())
                        .build())
                .build();

        return client;
    }
}

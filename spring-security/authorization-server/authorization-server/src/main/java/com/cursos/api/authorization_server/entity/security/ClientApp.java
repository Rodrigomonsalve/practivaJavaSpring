package com.cursos.api.authorization_server.entity.security;

import jakarta.persistence.*;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import java.util.List;
import java.util.Set;

//UN CLIENTE ES UNA APLICACIÓN, NO UN  USUARIO.
@Entity
public class ClientApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientId;

    private String clientSecret;//CONTRASEÑA
   // private Instant clientSecretExpiresAt;  //EN CASO DE QUE QUERAMOS QUE LA CONTRASEÑA EXPIRE

    //@ElementCollection sirve para crear una tabla por separado: client_app_clientAuthenticationMethods(se sigue esta convencion de nombre).
    // Automáticamente crea un registro con una llave foranea vinculada a la llave primaria de ClientApp, y un segundo registro con un valor de tipo, en este caso String.
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> clientAuthenticationMethods;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> authorizationGrantTypes;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> redirectUris;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> scopes;      //LOS SCOPES SON LOS AUTHORITIES, PERO PARA UN CLIENTE. HAY QUE RECORDAR QUE UN CLIENTE ES UNA APLICACIÓN, NO UN  USUARIO.

    private Long durationInMinutes;

    private boolean requiredProofKey;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public List<String> getClientAuthenticationMethods() {
        return clientAuthenticationMethods;
    }

    public void setClientAuthenticationMethods(List<String> clientAuthenticationMethods) {
        this.clientAuthenticationMethods = clientAuthenticationMethods;
    }

    public List<String> getAuthorizationGrantTypes() {
        return authorizationGrantTypes;
    }

    public void setAuthorizationGrantTypes(List<String> authorizationGrantTypes) {
        this.authorizationGrantTypes = authorizationGrantTypes;
    }

    public List<String> getRedirectUris() {
        return redirectUris;
    }

    public void setPostLogoutRedirectUris(List<String> redirectUris) {
        this.redirectUris = redirectUris;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public void setRedirectUris(List<String> redirectUris) {
        this.redirectUris = redirectUris;
    }

    public Long getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(Long durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public boolean isRequiredProofKey() {
        return requiredProofKey;
    }

    public void setRequiredProofKey(boolean requireProofKey) {
        this.requiredProofKey = requireProofKey;
    }
}

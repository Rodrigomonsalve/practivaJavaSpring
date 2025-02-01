package com.cursos.api.spring_security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;


//Este es el dto que tiene los datos para que se registre un nuevo cliente. Es un dto de la entidad "User".
 /*{

   "username": "Pepito02",
   "name": "Pepito Lopez",
   "password":"67463HEy",
   "repeatedPassword":"67463HEy"
}*/

//Ademas, se tiene que crear un dto que devuelva los datos del usuario ya registrado. Son 2 cosas diferentes. En este caso, es "RegisteredUser"
public class SaveUser implements Serializable {

    @Size(min = 4)
    private String name;

    private String username;

    @Size(min = 8)
    private String password;

    @Size(min = 8)
    private String repeatedPassword;

    public @Size(min = 4) String getName() {
        return name;
    }

    public void setName(@Size(min = 4) String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public @Size(min = 8) String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 8) String password) {
        this.password = password;
    }

    public @Size(min = 8) String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(@Size(min = 8) String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }
}

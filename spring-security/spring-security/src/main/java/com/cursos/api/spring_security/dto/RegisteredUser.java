package com.cursos.api.spring_security.dto;


import java.io.Serializable;

public class RegisteredUser implements Serializable {

    //Esta va a ser la respuesta al registrar a un cliente. Se hace en: http://localhost:9191/api/v1/customers  (POST)
    /*{
        "id": 1,
            "name": "Pepito Lopez",
            "username": "Pepito02",
            "role": "ROLE_CUSTOMER",
            "jwt": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9DVVNUT01FUiIsIm5hbWUiOiJQZXBpdG8gTG9wZXoiLCJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiUkVBRF9NWV9QUk9GSUxFIn1dLCJzdWIiOiJQZXBpdG8wMiIsImlhdCI6MTczODEwNzQ0MCwiZXhwIjoxNzM4MTA5MjQwfQ.wQgHIAeorDeEdjWAxBe4L8qibEhtyAAEbvSOENwIT4s"
    }*/

    private Long id;

    private String name;

    private String username;

    private String role;

    private String jwt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

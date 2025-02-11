package com.cursos.api.spring_security.persistence.entity.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "role", fetch=FetchType.EAGER)
    private List<GrantedPermissions> permissions;

    /*"id": 2,
            "username": "fperez",
            "name": "fulano pérez",
            "password": "$2a$10$V29z7/qC9wpHfzRMxGOHye5RMAxCid2/MzJalk0dsiA3zZ9CJfub.",
            "role": {
        "id": 2,
                "name": "ASSISTANT_ADMINISTRATOR",
                "permissions": [
        {
            "id": 2,
                "role": {
            "id": 2,
                    "name": "ASSISTANT_ADMINISTRATOR",
                    "permissions": [
            {
                "id": 2,
                    "role": {
                "id": 2,
                        "name": "ASSISTANT_ADMINISTRATOR",
                        "permissions": [
                {
                    "id": 2,
                        "role": {.......
                        ................*/



    /*
    {
    "id": 2,
    "username": "fperez",
    "name": "fulano pérez",
    "password": "$2a$10$V29z7/qC9wpHfzRMxGOHye5RMAxCid2/MzJalk0dsiA3zZ9CJfub.",
    "role": {
        "id": 2,
        "name": "ASSISTANT_ADMINISTRATOR"
    },
    "enabled": true,
    "accountNonLocked": true,
    "authorities": [
        {
            "authority": "READ_ALL_PRODUCTS"
        },
        {
            "authority": "READ_ONE_PRODUCT"
        },
        {
            "authority": "UPDATE_ONE_PRODUCT"
        },
        {
            "authority": "READ_ALL_CATEGORIES"
        },
        {
            "authority": "READ_ONE_CATEGORY"
        },
        {
            "authority": "UPDATE_ONE_CATEGORY"
        },
        {
            "authority": "READ_MY_PROFILE"
        },
        {
            "authority": "ROLE_ASSISTANT_ADMINISTRATOR"
        }
    ],
    "accountNonExpired": true,
    "credentialsNonExpired": true
}*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GrantedPermissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<GrantedPermissions> permissions) {
        this.permissions = permissions;
    }
}

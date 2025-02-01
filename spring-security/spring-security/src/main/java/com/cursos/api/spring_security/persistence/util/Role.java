package com.cursos.api.spring_security.persistence.util;

import java.util.Arrays;
import java.util.List;

//FORMA PARTE DE LA TABLA User. ES UN CAMPO.
public enum Role {

    //A los roles nunca se les debe anteponer la palaba ROLE_ . Esto porque al momento de crear una autorizacion, el metodO hasAnyRole agrega esta expresion para quedar, por ejemplo: ROLE_ADMINISTRATOR
    ADMINISTRATOR(Arrays.asList(
                        RolePermission.READ_ALL_PRODUCTS,
                        RolePermission.READ_ONE_PRODUCT,
                        RolePermission.CREATE_ONE_PRODUCT,
                        RolePermission.DISABLE_ONE_PRODUCT,
                        RolePermission.UPDATE_ONE_PRODUCT,

                        RolePermission.READ_ONE_CATEGORY,
                        RolePermission.CREATE_ONE_CATEGORY,
                        RolePermission.DISABLE_ONE_CATEGORY,
                        RolePermission.UPDATE_ONE_CATEGORY,

                        RolePermission.READ_MY_PROFILE
                        )),
    ASSISTANT_ADMINISTRATOR(Arrays.asList(
                        RolePermission.READ_ALL_PRODUCTS,
                        RolePermission.READ_ONE_PRODUCT,
                        RolePermission.UPDATE_ONE_PRODUCT,

                        RolePermission.READ_ONE_CATEGORY,
                        RolePermission.UPDATE_ONE_CATEGORY,

                        RolePermission.READ_MY_PROFILE
    )),
    CUSTOMER(Arrays.asList(RolePermission.READ_MY_PROFILE));

    private List<RolePermission> permissions;

    Role(List<RolePermission> permissions){
        this.permissions=permissions;
    }

    public List<RolePermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<RolePermission> permissions) {
        this.permissions = permissions;
    }
}

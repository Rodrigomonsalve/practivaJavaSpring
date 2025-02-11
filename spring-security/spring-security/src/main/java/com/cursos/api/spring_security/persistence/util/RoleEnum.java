package com.cursos.api.spring_security.persistence.util;

import java.util.Arrays;
import java.util.List;

//FORMA PARTE DE LA TABLA User. ES UN CAMPO.
public enum RoleEnum {

    //A los roles nunca se les debe anteponer la palaba ROLE_ . Esto porque al momento de crear una autorizacion, el metodO hasAnyRole agrega esta expresion para quedar, por ejemplo: ROLE_ADMINISTRATOR
    //El código, tal y como está ahora, ya no se usa esta enumeración. Hay 2 formas en que podemos obtener los permisos y roles:
    //1)Desde el código, como se hace con esta enumeracion.
    //2)Desde la base de datos. Como está actualmente la aplicacion.
    ADMINISTRATOR(Arrays.asList(
                        RolePermissionEnum.READ_ALL_PRODUCTS,
                        RolePermissionEnum.READ_ONE_PRODUCT,
                        RolePermissionEnum.CREATE_ONE_PRODUCT,
                        RolePermissionEnum.DISABLE_ONE_PRODUCT,
                        RolePermissionEnum.UPDATE_ONE_PRODUCT,

                        RolePermissionEnum.READ_ONE_CATEGORY,
                        RolePermissionEnum.CREATE_ONE_CATEGORY,
                        RolePermissionEnum.DISABLE_ONE_CATEGORY,
                        RolePermissionEnum.UPDATE_ONE_CATEGORY,

                        RolePermissionEnum.READ_MY_PROFILE
                        )),
    ASSISTANT_ADMINISTRATOR(Arrays.asList(
                        RolePermissionEnum.READ_ALL_PRODUCTS,
                        RolePermissionEnum.READ_ONE_PRODUCT,
                        RolePermissionEnum.UPDATE_ONE_PRODUCT,

                        RolePermissionEnum.READ_ONE_CATEGORY,
                        RolePermissionEnum.UPDATE_ONE_CATEGORY,

                        RolePermissionEnum.READ_MY_PROFILE
    )),
    CUSTOMER(Arrays.asList(RolePermissionEnum.READ_MY_PROFILE));

    private List<RolePermissionEnum> permissions;

    RoleEnum(List<RolePermissionEnum> permissions){
        this.permissions=permissions;
    }

    public List<RolePermissionEnum> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<RolePermissionEnum> permissions) {
        this.permissions = permissions;
    }
}

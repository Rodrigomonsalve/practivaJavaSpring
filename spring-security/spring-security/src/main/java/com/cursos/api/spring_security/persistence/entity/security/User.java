package com.cursos.api.spring_security.persistence.entity.security;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="\"user\"")  //EN REALIDAD SE LLAMA "user" NO User.  //ESTUDIAR ESTA EXPRESION
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String name;

    private String password;


    //El código, tal y como está ahora, ya no se usa esta enumeración. Hay 2 formas en que podemos obtener los permisos y roles:
    //1)Desde el código, como se hace con esta enumeracion.
    //2)Desde la base de datos. Como está actualmente la aplicacion. Los datos se obtienen desde el dto Role.

// @Enumerated(EnumType.STRING)
// private RoleEnum roleEnum;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {  //SE USA PARA OBTENER LOS ROLES Y PERMISOS DE UN USUARIO.


        if(role ==null){
            return null;
        }

        if(role.getPermissions()==null){
            return null;
        }

//        List<SimpleGrantedAuthority> authorities = role.getPermissions().stream().map(each->{
//            String permission=each.name();
//            return new SimpleGrantedAuthority(permission);
//        }).
//                collect(Collectors.toList());

        List<SimpleGrantedAuthority> authorities = role.getPermissions().stream()
                .map(each->each.getOperation().getName())
                .map(each->new SimpleGrantedAuthority(each))
                .collect(Collectors.toList());

        System.out.println("Estos son los authorities del usuario"+authorities);
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.role.getName()));
        return authorities;


    }

//    @Override
//    public String getPassword() {
//        return "";
//    }
//
//    @Override
//    public String getUsername() {
//        return "";
//

@Override
public String getPassword() {
    return password;
}

@Override
public String getUsername() {
    return username;
}

@Override
public boolean isAccountNonExpired() {
    return true;
}

@Override
public boolean isAccountNonLocked() {
    return true;
}

@Override
public boolean isCredentialsNonExpired() {
    return true;
}

@Override
public boolean isEnabled() {
    return true;
}

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public void setUsername(String username) {
    this.username = username;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public void setPassword(String password) {
    this.password = password;
}

public Role getRole() {
    return role;
}

public void setRole(Role role) {
    this.role = role;
}
}


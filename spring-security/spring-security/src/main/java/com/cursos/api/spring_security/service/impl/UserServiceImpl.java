package com.cursos.api.spring_security.service.impl;

import com.cursos.api.spring_security.dto.SaveUser;
import com.cursos.api.spring_security.exceptions.InvalidPasswordException;
import com.cursos.api.spring_security.exceptions.ObjectNotFoundException;
import com.cursos.api.spring_security.persistence.entity.security.Role;
import com.cursos.api.spring_security.persistence.entity.security.User;
import com.cursos.api.spring_security.persistence.repository.security.UserRepository;
import com.cursos.api.spring_security.service.RoleService;
import com.cursos.api.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Override
    public User registerOneCustomer(SaveUser newUser) {  //SaveUser es el dto de User que sirve para registrar nuevos clientes. Se invoca desde AuthenticationService, el cual es invocado a su vez por CustomerController.

        validatePassword(newUser); //Antes de guardarse en base de datos, se valida si la contraseña es igual al campo repeatedPassword.

        User user=new User();
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setUsername(newUser.getUsername());
        user.setName(newUser.getName());
        //user.setRole(RoleEnum.CUSTOMER);

        Role defaultRole=roleService.findDefaultRole()
                        .orElseThrow(()->new ObjectNotFoundException("Role not found"));
        user.setRole(defaultRole);


        return userRepository.save(user);
    }

    @Override
    public Optional<User> findOneByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    private void validatePassword(SaveUser dto) {

        if(!StringUtils.hasText(dto.getPassword()) || !StringUtils.hasText(dto.getRepeatedPassword())){
            throw new InvalidPasswordException("Passwords dont match");
        }

        if(!dto.getPassword().equals(dto.getRepeatedPassword())){
            throw new InvalidPasswordException("Passwords dont match");
        }
    }

}

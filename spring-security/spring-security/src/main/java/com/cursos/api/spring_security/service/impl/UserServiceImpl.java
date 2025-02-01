package com.cursos.api.spring_security.service.impl;

import com.cursos.api.spring_security.dto.SaveUser;
import com.cursos.api.spring_security.exceptions.InvalidPasswordException;
import com.cursos.api.spring_security.persistence.entity.User;
import com.cursos.api.spring_security.persistence.repository.UserRepository;
import com.cursos.api.spring_security.persistence.util.Role;
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

    @Override
    public User registerOneCustomer(SaveUser newUser) {  //SaveUser es el dto de User que sirve para registrar nuevos clientes. Se invoca desde AuthenticationService, el cual es invocado a su vez por CustomerController.

        validatePassword(newUser); //Antes de guardarse en base de datos, se valida si la contraseña es igual al campo repeatedPassword.

        User user=new User();
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setUsername(newUser.getUsername());
        user.setName(newUser.getName());
        user.setRole(Role.CUSTOMER);

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

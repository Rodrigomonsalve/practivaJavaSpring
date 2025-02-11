package com.cursos.api.spring_security.service;


import com.cursos.api.spring_security.dto.SaveUser;
import com.cursos.api.spring_security.persistence.entity.security.User;
import jakarta.validation.Valid;

import java.util.Optional;

public interface UserService {
    User registerOneCustomer(@Valid SaveUser newUser);
    Optional<User> findOneByUsername(String username);
}

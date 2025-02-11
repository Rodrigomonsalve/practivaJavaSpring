package com.cursos.api.spring_security.service;

import com.cursos.api.spring_security.persistence.entity.security.Role;

import java.util.Optional;

public interface RoleService {

    public Optional<Role> findDefaultRole();
}

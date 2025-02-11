package com.cursos.api.spring_security.service.impl;

import com.cursos.api.spring_security.persistence.entity.security.Role;
import com.cursos.api.spring_security.persistence.repository.security.RoleRepository;
import com.cursos.api.spring_security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Value("${security.default.role}")
    private String defaultRole;

    @Override
    public Optional<Role> findDefaultRole() {
        return roleRepository.findByName(defaultRole);
    }
}

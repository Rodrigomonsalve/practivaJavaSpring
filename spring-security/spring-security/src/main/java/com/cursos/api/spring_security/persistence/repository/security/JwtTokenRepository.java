package com.cursos.api.spring_security.persistence.repository.security;


import com.cursos.api.spring_security.persistence.entity.security.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {

    Optional<JwtToken> findByToken(String jwt);
}

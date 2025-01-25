package com.cursos.api.spring_security.persistence.repository;

import com.cursos.api.spring_security.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

package com.cursos.api.spring_security.service;

import com.cursos.api.spring_security.dto.SaveCategory;
import com.cursos.api.spring_security.persistence.entity.Category;
import com.cursos.api.spring_security.persistence.entity.Product;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public interface CategoryService {

    public Page<Category> findAll(Pageable pageable);

    public Optional<Category> findById(Long categoryId);

    Category createOne(SaveCategory saveCategory);

    Category disableOneById(Long categoryId);

    Category updateOneById(Long categoryId, SaveCategory saveCategory);
}

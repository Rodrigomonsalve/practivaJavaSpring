package com.cursos.api.spring_security.service;

import com.cursos.api.spring_security.dto.SaveCategory;
import com.cursos.api.spring_security.dto.SaveProduct;
import com.cursos.api.spring_security.persistence.entity.Product;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public interface ProductService {


    public Page<Product> findAll(Pageable pageable);

    public Optional<Product> findById(Long productId);

    Product createOne(SaveProduct saveProduct);

    Product updateOneById(Long productId, SaveProduct saveProduct);

    Product disableOneById(Long productId);
}

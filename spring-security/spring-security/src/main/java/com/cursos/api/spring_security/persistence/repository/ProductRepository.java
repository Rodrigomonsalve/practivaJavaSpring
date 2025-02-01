package com.cursos.api.spring_security.persistence.repository;

import com.cursos.api.spring_security.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductRepository extends JpaRepository<Product, Long> {


}

package com.cursos.api.spring_security.service.impl;

import com.cursos.api.spring_security.dto.SaveCategory;
import com.cursos.api.spring_security.dto.SaveProduct;
import com.cursos.api.spring_security.exceptions.ObjectNotFoundException;
import com.cursos.api.spring_security.persistence.entity.Category;
import com.cursos.api.spring_security.persistence.entity.Product;
import com.cursos.api.spring_security.persistence.repository.ProductRepository;
import com.cursos.api.spring_security.service.CategoryService;
import com.cursos.api.spring_security.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Product createOne(SaveProduct saveProduct) {
        Product product=new Product();
        product.setName(saveProduct.getName());
        product.setPrice(saveProduct.getPrice());
        product.setStatus(Product.ProductStatus.ENABLED);

        Category category=new Category();
        category.setId(saveProduct.getCategoryId());
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Product updateOneById(Long productId, SaveProduct saveProduct) {
        Product productFromDB=productRepository.findById(productId).orElseThrow(()-> new ObjectNotFoundException("Product not found with ID"));
        productFromDB.setName(saveProduct.getName());
        productFromDB.setPrice(saveProduct.getPrice());

        Category category=new Category();
        category.setId(saveProduct.getCategoryId());
        productFromDB.setCategory(category);
        return productRepository.save(productFromDB);
    }

    @Override
    public Product disableOneById(Long productId) {
        Product productFromDB=productRepository.findById(productId).orElseThrow(()-> new ObjectNotFoundException("Product not found with ID"));
        productFromDB.setStatus(Product.ProductStatus.DISABLED);
        return productRepository.save(productFromDB);
    }
}

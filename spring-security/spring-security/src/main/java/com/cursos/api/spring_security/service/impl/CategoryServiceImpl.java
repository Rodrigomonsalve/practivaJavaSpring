package com.cursos.api.spring_security.service.impl;

import com.cursos.api.spring_security.dto.SaveCategory;
import com.cursos.api.spring_security.dto.SaveProduct;
import com.cursos.api.spring_security.exceptions.ObjectNotFoundException;
import com.cursos.api.spring_security.persistence.entity.Category;
import com.cursos.api.spring_security.persistence.entity.Product;
import com.cursos.api.spring_security.persistence.repository.CategoryRepository;
import com.cursos.api.spring_security.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Optional<Category> findById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public Category createOne(SaveCategory saveCategory) {
        Category category=new Category();
        category.setName(saveCategory.getName());
        category.setStatus(Category.CategoryStatus.ENABLED);

        return categoryRepository.save(category);
    }

    @Override
    public Category updateOneById(Long categoryId, SaveCategory saveCategory) {
        Category categoryFromDB=categoryRepository.findById(categoryId).orElseThrow(()-> new ObjectNotFoundException("Category not found with ID "+categoryId));
        categoryFromDB.setName(saveCategory.getName());

        return categoryRepository.save(categoryFromDB);
    }

    @Override
    public Category disableOneById(Long categoryId) {
        Category categoryFromDB=categoryRepository.findById(categoryId).orElseThrow(()-> new ObjectNotFoundException("Category not found with ID "+categoryId));
        categoryFromDB.setStatus(Category.CategoryStatus.DISABLED);
        return categoryRepository.save(categoryFromDB);
    }
}

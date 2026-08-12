package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.entity.Category;
import com.postgrado.ecommerce.exception.EntityNotFoundException;
import com.postgrado.ecommerce.repository.CategoryRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public Category getById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Category", id));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}

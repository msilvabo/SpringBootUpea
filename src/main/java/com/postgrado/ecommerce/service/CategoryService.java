package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    Category getById(UUID id);

    List<Category> getAllCategories();

    Category createCategory(Category category);

    Category updateCategory(UUID id, Category category);

    void deleteCategory(UUID id);
}

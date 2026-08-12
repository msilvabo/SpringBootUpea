package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    Category getById(UUID id);

    List<Category> getAllCategories();
}

package com.postgrado.ecommerce.controller;

import com.postgrado.ecommerce.entity.Category;
import com.postgrado.ecommerce.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable UUID id){
        Category categoryFound = categoryService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryFound);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

}

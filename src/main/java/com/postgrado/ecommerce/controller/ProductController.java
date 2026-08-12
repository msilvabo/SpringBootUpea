package com.postgrado.ecommerce.controller;

import com.postgrado.ecommerce.dto.ProductDto;
import com.postgrado.ecommerce.entity.Product;
import com.postgrado.ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> create (@RequestBody ProductDto dto){
        Product productCreate = productService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable UUID id){
        Product product = productService.getbyId(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
}

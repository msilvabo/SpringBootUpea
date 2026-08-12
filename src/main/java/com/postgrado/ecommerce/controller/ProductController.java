package com.postgrado.ecommerce.controller;

import com.postgrado.ecommerce.dto.ProductDto;
import com.postgrado.ecommerce.entity.Product;
import com.postgrado.ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        Product product = productService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Product>> getProducts(@RequestParam int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Product> productsPage = productService.getProducts(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(productsPage);
    }
}

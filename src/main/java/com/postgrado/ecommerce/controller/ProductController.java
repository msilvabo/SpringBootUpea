package com.postgrado.ecommerce.controller;

import com.postgrado.ecommerce.dto.PageDto;
import com.postgrado.ecommerce.dto.ProductDto;
import com.postgrado.ecommerce.entity.Product;
import com.postgrado.ecommerce.service.ProductService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Tag(name="Product")
@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @SecurityRequirement(name="bearerAuth")
    @Operation(summary = "Create new product")
    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody ProductDto dto) {
        Product productCreate = productService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);
    }

    @Operation(summary = "Get Product by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable UUID id) {
        Product product = productService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @Operation(summary = "List Products with pagination")
    @GetMapping("/pageable")
    public ResponseEntity<Page<Product>> getProducts(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productsPage = productService.getProducts(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(productsPage);
    }

    @Operation(summary = "Get Products Filter")
    @GetMapping()
    public ResponseEntity<Page<Product>> getFilterProducts(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortField,
            @RequestParam String sortOrder
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product> productsPage = productService.getFilteredProducts(minPrice, maxPrice, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(productsPage);
    }

    @Hidden
    @Operation(summary = "Get Product Filter Dto")
    @GetMapping("/dto")
    public ResponseEntity<PageDto<Product>> getFilterProductsDto(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortField,
            @RequestParam String sortOrder
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);

        PageDto<Product> productsPage = productService.getFilteredProductsDto(minPrice, maxPrice, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(productsPage);
    }
}

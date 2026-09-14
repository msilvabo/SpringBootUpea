package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.PageDto;
import com.postgrado.ecommerce.dto.ProductDto;
import com.postgrado.ecommerce.entity.Product;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProductService {
    Product createProduct(ProductDto product);

    Product getById(UUID id);

    Page<Product> getProducts(Pageable pageable, Boolean active, String term, java.util.List<UUID> categoryIds);
    default Page<Product> getProducts(Pageable pageable, Boolean active, String term) {
        return getProducts(pageable, active, term, null);
    }
    default Page<Product> getProducts(Pageable pageable, Boolean active) {
        return getProducts(pageable, active, null, null);
    }

    Page<Product> getFilteredProducts(Double priceMin, Double priceMax, Pageable pageable);

    PageDto<Product> getFilteredProductsDto(Double priceMin, Double priceMax, Pageable pageable);

    Product updateProduct(UUID id, ProductDto productDto);
}



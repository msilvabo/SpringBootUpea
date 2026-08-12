package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.ProductDto;
import com.postgrado.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product createProduct(ProductDto product);

    Product getById(UUID id);

    Page<Product> getProducts(Pageable pageable);
}



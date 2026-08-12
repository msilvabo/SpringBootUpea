package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.ProductDto;
import com.postgrado.ecommerce.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product createProduct(ProductDto product);
    Product getbyId(UUID id);
}



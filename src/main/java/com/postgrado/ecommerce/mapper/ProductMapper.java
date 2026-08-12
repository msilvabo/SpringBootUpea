package com.postgrado.ecommerce.mapper;

import com.postgrado.ecommerce.dto.ProductDto;
import com.postgrado.ecommerce.entity.Product;
import org.springframework.stereotype.Component;


@Component
public class ProductMapper {

    public Product fromProductDtc (ProductDto dto){
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setImageUrl(dto.getImageUrl());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setActive(dto.isActive());

        return product;
    }
}

package com.postgrado.ecommerce.mapper;

import com.postgrado.ecommerce.dto.PageDto;
import com.postgrado.ecommerce.dto.ProductDto;
import com.postgrado.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
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

    public PageDto<Product> fromEntity (Page<Product> page){
        PageDto<Product> dto = new PageDto<>();
        dto.setContent(page.getContent());
        dto.setLast(page.isLast());
        dto.setPageNumber(page.getNumber());
        dto.setPageSize(page.getSize());
        dto.setTotalPages(page.getTotalPages());
        dto.setTotalElements(page.getTotalElements());
        return dto;
    }
}

package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.ProductDto;
import com.postgrado.ecommerce.entity.Category;
import com.postgrado.ecommerce.entity.Product;
import com.postgrado.ecommerce.exception.EntityNotFoundException;
import com.postgrado.ecommerce.mapper.ProductMapper;
import com.postgrado.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CategoryService categoryService;
    private ProductMapper productMapper;

    @Override
    public Product createProduct(ProductDto dto) {

        Category category = categoryService.getById(dto.getCategoryId());

        Product product = productMapper.fromProductDtc(dto);
        product.setCategory(category);

        return productRepository.save(product);
    }

    @Override
    public Product getbyId(UUID id) {
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Product", id));
    }
}


package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.ProductDto;
import com.postgrado.ecommerce.entity.Category;
import com.postgrado.ecommerce.entity.Product;
import com.postgrado.ecommerce.exception.EntityNotFoundException;
import com.postgrado.ecommerce.mapper.ProductMapper;
import com.postgrado.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Product getById(UUID id) {
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Product", id));
    }

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> getFilteredProducts(Double priceMin, Double priceMax, Pageable pageable) {
        return productRepository.findByPriceBetween(priceMin, priceMax, pageable);
    }
}


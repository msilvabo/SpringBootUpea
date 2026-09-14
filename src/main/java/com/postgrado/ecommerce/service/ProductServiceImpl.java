package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.PageDto;
import com.postgrado.ecommerce.dto.ProductDto;
import com.postgrado.ecommerce.entity.Category;
import com.postgrado.ecommerce.entity.Product;
import com.postgrado.ecommerce.exception.EntityNotFoundException;
import com.postgrado.ecommerce.mapper.ProductMapper;
import com.postgrado.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public Page<Product> getProducts(Pageable pageable, Boolean active, String term, java.util.List<UUID> categoryIds) {
        String cleanTerm = (term != null && !term.trim().isEmpty()) ? term.trim() : null;
        if (categoryIds != null && !categoryIds.isEmpty()) {
            return productRepository.searchProductsWithCategories(cleanTerm, active, categoryIds, pageable);
        }
        if (cleanTerm != null) {
            return productRepository.searchProducts(cleanTerm, active, pageable);
        }
        if (active != null) {
            return productRepository.findByActive(active, pageable);
        }
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> getProducts(Pageable pageable, Boolean active, String term) {
        return getProducts(pageable, active, term, null);
    }

    @Override
    public Page<Product> getProducts(Pageable pageable, Boolean active) {
        return getProducts(pageable, active, null, null);
    }

    @Override
    public Page<Product> getFilteredProducts(Double priceMin, Double priceMax, Pageable pageable) {
        return productRepository.findByPriceBetween(priceMin, priceMax, pageable);
    }

    @Override
    public PageDto<Product> getFilteredProductsDto(Double priceMin, Double priceMax, Pageable pageable) {
        Page<Product> page = productRepository.findByPriceBetween(priceMin, priceMax, pageable);
        return productMapper.fromEntity(page);
    }

    @Override
    public Product updateProduct(UUID id, ProductDto dto) {
        Product existingProduct = productRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("Product", id));
        Category category = categoryService.getById(dto.getCategoryId());
        existingProduct.setName(dto.getName());
        existingProduct.setDescription(dto.getDescription());
        existingProduct.setImageUrl(dto.getImageUrl());
        existingProduct.setPrice(dto.getPrice());
        existingProduct.setStock(dto.getStock());
        existingProduct.setActive(dto.isActive());
        existingProduct.setCategory(category);

        return productRepository.save(existingProduct);
    }
}


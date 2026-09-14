package com.postgrado.ecommerce.repository;

import com.postgrado.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Page<Product> findByPriceBetween(Double priceMMin, Double priceMax, Pageable pageable);
    Page<Product> findByActive(Boolean active, Pageable pageable);

    @Query("SELECT p FROM Product p LEFT JOIN p.category c WHERE " +
           "(:active IS NULL OR p.active = :active) AND " +
           "(:term IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', CAST(:term AS string), '%')) " +
           "OR LOWER(p.description) LIKE LOWER(CONCAT('%', CAST(:term AS string), '%')) " +
           "OR LOWER(c.name) LIKE LOWER(CONCAT('%', CAST(:term AS string), '%')))")
    Page<Product> searchProducts(@Param("term") String term, @Param("active") Boolean active, Pageable pageable);

    @Query("SELECT p FROM Product p LEFT JOIN p.category c WHERE " +
           "(:active IS NULL OR p.active = :active) AND " +
           "c.id IN :categoryIds AND " +
           "(:term IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', CAST(:term AS string), '%')) " +
           "OR LOWER(p.description) LIKE LOWER(CONCAT('%', CAST(:term AS string), '%')) " +
           "OR LOWER(c.name) LIKE LOWER(CONCAT('%', CAST(:term AS string), '%')))")
    Page<Product> searchProductsWithCategories(@Param("term") String term, @Param("active") Boolean active, @Param("categoryIds") java.util.List<UUID> categoryIds, Pageable pageable);
}

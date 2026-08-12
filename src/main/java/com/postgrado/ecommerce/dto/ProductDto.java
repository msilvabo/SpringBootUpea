package com.postgrado.ecommerce.dto;

import com.postgrado.ecommerce.entity.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    private int stock;
    private boolean active;

    private UUID categoryId;
}

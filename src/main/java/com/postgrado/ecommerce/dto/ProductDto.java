package com.postgrado.ecommerce.dto;

import com.postgrado.ecommerce.entity.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    @NotBlank(message = "{product.name.not-blank}")
    @Size(min = 5, max = 25, message = "{product.name.size}")
    private String name;
    @NotBlank(message = "{product.description.not-blank}")
    private String description;
    @URL
    private String imageUrl;
    @NonNull
    @DecimalMin(value = "0.0", inclusive = false, message = "{product.price.min}")
    private double price;
    @NonNull
    @Min(value = 0, message = "{product.stock.min}")
    private int stock;
    private boolean active;
    @NonNull
    private UUID categoryId;
}

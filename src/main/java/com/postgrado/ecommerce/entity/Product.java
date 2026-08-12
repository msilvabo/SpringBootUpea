package com.postgrado.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(length = 70, nullable = false)
    private String name;
    @Column(length = 255, nullable = false)
    private String description;
    @Column(length = 255, nullable = false)
    private String imageUrl;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Integer stock;
    @Column(nullable = false)
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}

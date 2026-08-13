package com.postgrado.ecommerce.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class OrderItemDto {
    private Integer quantity;
    private UUID productId;
}

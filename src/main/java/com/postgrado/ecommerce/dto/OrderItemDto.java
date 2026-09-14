package com.postgrado.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String productName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double totalPrice;

    public OrderItemDto(UUID productId, Integer quantity, Double totalPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public OrderItemDto(UUID productId, String productName, Integer quantity, Double totalPrice) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}

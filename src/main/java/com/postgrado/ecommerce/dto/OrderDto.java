package com.postgrado.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.postgrado.ecommerce.entity.OrderState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OrderDto {
    private String comment;
    private List<OrderItemDto> items;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double totalPrice;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OrderState state;
}

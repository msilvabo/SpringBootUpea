package com.postgrado.ecommerce.dto;

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
}

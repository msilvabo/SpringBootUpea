package com.postgrado.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.postgrado.ecommerce.entity.OrderState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class OrderDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    private LocalDateTime date;

    private String comment;
    private List<OrderItemDto> items;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double totalPrice;

    private OrderState state;
}

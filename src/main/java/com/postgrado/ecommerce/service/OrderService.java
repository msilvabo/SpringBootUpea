package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.OrderDto;
import com.postgrado.ecommerce.dto.OrderItemDto;
import com.postgrado.ecommerce.entity.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    String create(OrderDto order);

    Double totalPrice(UUID id);

    List<OrderItemDto> items (UUID id);

    OrderDto getById(UUID id);
}

package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.OrderDto;
import com.postgrado.ecommerce.dto.OrderItemDto;
import com.postgrado.ecommerce.dto.ProductDto;
import com.postgrado.ecommerce.entity.Order;
import com.postgrado.ecommerce.entity.OrderState;
import com.postgrado.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    String create(OrderDto order);

    Double totalPrice(UUID id);

    List<OrderItemDto> items (UUID id);

    OrderDto getById(UUID id);

    Page<OrderDto> getOrders(Pageable pageable);

    Order updateOrder(UUID id, OrderDto orderDto);

    Void updateState (UUID id, OrderState orderState);
}

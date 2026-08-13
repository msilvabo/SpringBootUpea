package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.OrderDto;
import com.postgrado.ecommerce.entity.Order;

public interface OrderService {
    String create(OrderDto order);
}

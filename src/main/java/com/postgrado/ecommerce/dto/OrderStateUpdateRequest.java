package com.postgrado.ecommerce.dto;

import com.postgrado.ecommerce.entity.OrderState;

public record OrderStateUpdateRequest(OrderState status) {}
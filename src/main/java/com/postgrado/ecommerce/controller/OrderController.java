package com.postgrado.ecommerce.controller;

import com.postgrado.ecommerce.dto.OrderDto;
import com.postgrado.ecommerce.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    @PostMapping
    public ResponseEntity<String> create (@RequestBody OrderDto order){
        String message = orderService.create(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
}
